from flask import Flask
from public import public
from admin import admin
from expert import expert
from user import user
from api import api

app=Flask(__name__)

app.secret_key="aaa"

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix="/admin")
app.register_blueprint(expert,url_prefix="/expert")
app.register_blueprint(user,url_prefix="/user")
app.register_blueprint(api,url_prefix="/api")



app.run(debug=False,port=5081,host="0.0.0.0")	