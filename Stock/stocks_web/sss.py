import numpy as np
import matplotlib.pyplot as plt
plt.style.use('fivethirtyeight')
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential, load_model
from tensorflow.keras.layers import Dense, LSTM, Dropout, GRU
from tensorflow.keras.optimizers import SGD
import math
from sklearn.metrics import mean_squared_error

def plot_predictions(test, predicted):
    print("=================================")
    print(predicted)
    plt.plot(test, color='red', label='Actual Data')
    plt.plot(predicted, color='blue', label='Predicted Data')
    plt.title('Visiting Records Prediction')
    plt.xlabel('Time')
    plt.ylabel('Visiting Count')
    plt.legend()
    plt.show()

def return_rmse(test, predicted):
    rmse = math.sqrt(mean_squared_error(test, predicted))
    print("The root mean squared error is {}.".format(rmse))

def train_gru_model(training_set):
    sc = MinMaxScaler(feature_range=(0, 1))
    training_set_scaled = sc.fit_transform(training_set)

    X_train, y_train = [], []
    for i in range(60, len(training_set_scaled)):
        X_train.append(training_set_scaled[i - 60:i, 0])
        y_train.append(training_set_scaled[i, 0])
    X_train, y_train = np.array(X_train), np.array(y_train)

    X_train = np.reshape(X_train, (X_train.shape[0], X_train.shape[1], 1))

    regressorGRU = Sequential()
    regressorGRU.add(GRU(units=50, return_sequences=True, input_shape=(X_train.shape[1], 1), activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, return_sequences=True, input_shape=(X_train.shape[1], 1), activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, return_sequences=True, input_shape=(X_train.shape[1], 1), activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(GRU(units=50, activation='tanh'))
    regressorGRU.add(Dropout(0.2))
    regressorGRU.add(Dense(units=1))
    regressorGRU.compile(optimizer=SGD(lr=0.01, decay=1e-7, momentum=0.9, nesterov=False), loss='mean_squared_error')
    regressorGRU.fit(X_train, y_train, epochs=1, batch_size=150)

    regressorGRU.save('gru_model.h5')  # Save the trained model
    return sc

def predict_gru_model(sc, test_set):
    # Load the trained model
    regressorGRU = load_model('gru_model.h5')

    dataset_total = pd.concat((pd.DataFrame(test_set[:'2020'], columns=['close']),
                              pd.DataFrame(test_set['2021':], columns=['close'])), axis=0)

    # Convert date indices to datetime
    dataset_total.index = pd.to_datetime(dataset_total.index)

    inputs = dataset_total.iloc[len(dataset_total) - len(test_set) - 60:].values
    inputs = inputs.reshape(-1, 1)
    inputs = sc.transform(inputs)

    X_test, y_test = [], []

    for i in range(60, 100):
        X_test.append(inputs[i - 60:i, 0])
        y_test.append(inputs[i, 0])

    X_test = np.array(X_test)
    X_test = np.reshape(X_test, (X_test.shape[0], X_test.shape[1], 1))

    GRU_predicted_stock_price = regressorGRU.predict(X_test)
    GRU_predicted_stock_price = sc.inverse_transform(GRU_predicted_stock_price)

    return y_test, GRU_predicted_stock_price


def gru_aave():
    dataset = pd.read_csv(r'C:\\Users\\jahan\\OneDrive\\Desktop\\Stock\\Stock\\stocks_web\\ibm.csv', index_col='date', parse_dates=['date'])
    print(dataset.head())

    training_set = dataset.loc[:'2020'].iloc[:, 3:4].values
    test_set = dataset.loc['2021':].iloc[:, 3:4].values

    sc = train_gru_model(training_set)
    y_test, GRU_predicted_stock_price = predict_gru_model(sc, test_set)

    return_rmse(y_test, GRU_predicted_stock_price)
    plot_predictions(y_test, GRU_predicted_stock_price)

# Call the gru_aave() function to execute the code
gru_aave()
