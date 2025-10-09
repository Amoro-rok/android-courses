from flask import Flask
app = Flask('myapi')
@app.route('/myendpoint')
def myfunc():
    return 'Hello, World!'
