import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeRegressor
from sklearn.metrics import mean_squared_error
import joblib

# Load dataset from CSV file
df = pd.read_csv("ibm.csv")

# Features (X) and target (y)
X = df[['open', 'high', 'low', 'close']]
y = df['volume']

# Splitting the data into training and testing sets (80% train, 20% test)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Training the decision tree regressor
model = DecisionTreeRegressor(random_state=42)
model.fit(X_train, y_train)

# Saving the model to disk
joblib.dump(model, 'decision_tree_model.pkl')

# Calculate Mean Squared Error (MSE) on test set
predictions = model.predict(X_test)
mse = mean_squared_error(y_test, predictions)
print("Mean Squared Error:", mse)

# Loading the model from disk
loaded_model = joblib.load('decision_tree_model.pkl')

# Example prediction for new data
new_data = [[108, 112, 105, 110]]
prediction_new = loaded_model.predict(new_data)
print("Predicted Volume for new data point:", prediction_new[0])
