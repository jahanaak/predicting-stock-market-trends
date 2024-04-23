import numpy as np
import matplotlib.pyplot as plt
plt.style.use('fivethirtyeight')
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, GRU, Dropout
from tensorflow.keras.optimizers import SGD
from tensorflow.keras.optimizers.schedules import ExponentialDecay
import math
from sklearn.metrics import mean_squared_error

def gru_aave():
    def plot_predictions(test, predicted):
        plt.plot(test, color='red', label='Actual visiting records')
        plt.plot(predicted, color='blue', label='Predicted visiting records')
        plt.title('Visiting Records Prediction')
        plt.xlabel('Time')
        plt.ylabel('Visiting Count')
        plt.legend()
        plt.show()

    def return_rmse(test, predicted):
        rmse = math.sqrt(mean_squared_error(test, predicted))
        print("Root Mean Squared Error: {}".format(rmse))

    # Load the dataset
    dataset = pd.read_csv(r'C:\\Users\\jahan\\OneDrive\\Desktop\\Stock\\Stock\\stocks_web\\ibm.csv', index_col='date', parse_dates=['date'])

    training_set = dataset[:'2020'].iloc[:, 3:4].values
    test_set = dataset['2021':].iloc[:, 3:4].values

    # Scaling the training set
    sc = MinMaxScaler(feature_range=(0, 1))
    training_set_scaled = sc.fit_transform(training_set)

    X_train = []
    y_train = []
    for i in range(60, len(training_set_scaled)):
        X_train.append(training_set_scaled[i - 60:i, 0])
        y_train.append(training_set_scaled[i, 0])
    X_train, y_train = np.array(X_train), np.array(y_train)

    X_train = np.reshape(X_train, (X_train.shape[0], X_train.shape[1], 1))

    # The GRU architecture
    regressorGRU = Sequential()
    regressorGRU.add(GRU(units=50, return_sequences=True, input_shape=(X_train.shape[1], 1), activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, return_sequences=True, activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, return_sequences=True, activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(Dense(units=1))

    # Compiling the RNN with a learning rate schedule
    learning_rate_schedule = ExponentialDecay(initial_learning_rate=0.01, decay_steps=100000, decay_rate=0.96, staircase=True)
    regressorGRU.compile(optimizer=SGD(learning_rate=learning_rate_schedule), loss='mean_squared_error')

    # Fitting to the training set
    regressorGRU.fit(X_train, y_train, epochs=1, batch_size=32)

    # Prepare the test set
    dataset_total = pd.concat((dataset["close"][:'2020'], dataset["close"]['2021':]), axis=0)
    inputs = dataset_total[len(dataset_total) - len(test_set) - 60:].values
    inputs = inputs.reshape(-1, 1)
    inputs = sc.transform(inputs)

    X_test = []
    y_test = []
    for i in range(60, 100):
        X_test.append(inputs[i - 60:i, 0])
        y_test.append(inputs[i, 0])

    X_test = np.array(X_test)
    X_test = np.reshape(X_test, (X_test.shape[0], X_test.shape[1], 1))

    # Predictions
    GRU_predicted_stock_price = regressorGRU.predict(X_test)
    GRU_predicted_stock_price = sc.inverse_transform(GRU_predicted_stock_price)

    # Evaluate and visualize results
    # return_rmse(test_set[:40], GRU_predicted_stock_price[:40])
    # plot_predictions(test_set[:40], GRU_predicted_stock_price[:40])
    return(GRU_predicted_stock_price)
# Run the function
# gru_aave()
