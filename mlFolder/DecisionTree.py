import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import pickle

# read_csv returns a Pandas dataframe
df = pd.read_csv('filtered_dbV2.csv')

def decTreeScore(xValue , yValue, maxDepth = None, minSamples = 1, minSplit =2):
    x_train, x_test, y_train, y_test = train_test_split(xValue,yValue,random_state=0)

    dect = DecisionTreeClassifier(max_depth = maxDepth, min_samples_leaf = minSamples, min_samples_split = minSplit, random_state = 0)
    dect.fit(x_train, y_train)
    y_pred = dect.predict(x_test)
    accuracy = accuracy_score(y_test, y_pred)
    print(accuracy)
    return dect

x = df.iloc[:,0:-1]
y = df.iloc[:,-1]

#test in jupyter show that maxDepth = 9 and minSamples = 10 have the best accuracy
dect = decTreeScore(xValue = x, yValue = y, maxDepth = 9, minSamples = 10)

pickle.dump(dect, open('DecTree.pkl', 'wb'))  #serialize the object
