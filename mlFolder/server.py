import pandas as pd
from flask import Flask, request, jsonify
import pickle

app = Flask(__name__)


@app.route('/model', methods=['POST'])
def callDecisionTree():
    xValue = [request.json['leadTime'],request.json['weekendNights'],request.json["numNights"],request.json["averageRate"],request.json["numSpecialRequest"]]
    model = pickle.load(open('DecTree.pkl', 'rb')) # load model to the server
    return str(model.predict([xValue])[0])

# run the server
if __name__ == '__main__':
    app.run(port=5000, debug=True)
