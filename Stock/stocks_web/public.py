from flask import *
from database import *
from gru_python_aave import *


public=Blueprint('public',__name__)

@public.route('/')
def index():
	return render_template('index.html')

@public.route('/user_register',methods=['post','get'])
def user_register():
	if'us' in request.form:
		fname=request.form['fname']
		lname=request.form['lname']
		state=request.form['state']
		po=request.form['po']
		pi=request.form['pi']
		phn=request.form['phn']
		mail=request.form['mail']
		uname=request.form['uname']
		passw=request.form['passw']
		q="insert into slogin values(null,'%s','%s','user')"%(uname,passw)
		res=insert(q)
		q1="insert into user values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(res,fname,lname,state,po,pi,phn,mail)
		insert(q1)
	return render_template('user_register.html')

@public.route('/login',methods=['post','get'])
def login():
	if 'ls' in request.form:
		uname=request.form['uname']
		passw=request.form['passw']
		q="select * from slogin where username='%s' and password='%s'"%(uname,passw)
		val=select(q)
		if val:
			session['lid']=val[0]['login_id']

			if val[0]['usertype']=='expert':
				r="select * from expert where login_id='%s'"%(session['lid'])
				res=select(r)
				if res:
					session['eid']=res[0]['expert_id']
					print("*00"*100)
					print(session['eid'])
				return redirect(url_for('expert.expert_home'))

			elif val[0]['usertype']=='admin':
				return redirect(url_for('admin.admin_home'))

			elif val[0]['usertype']=='user':
				r="select * from user where login_id='%s'"%(session['lid'])
				res=select(r)
				if res:
					session['uid']=res[0]['user_id']
				return redirect(url_for('user.user_home'))
	return render_template('login.html')

# @public.route('/expert_register',methods=['post','get'])
# def expert_register():
# 	if 'es' in request.form:
# 		fname=request.form['fname']
# 		lname=request.form['lname']
# 		state=request.form['state']
# 		po=request.form['po']
# 		pi=request.form['pi']
# 		phn=request.form['phn']
# 		mail=request.form['mail']
# 		uname=request.form['uname']
# 		passw=request.form['passw']
# 		q="insert into slogin values(null,'%s','%s','expert')"%(uname,passw)
# 		res=insert(q)
# 		q1="insert into expert values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(res,fname,lname,state,po,pi,phn,mail)
# 		insert(q1)
# 	return render_template('expert_register.html')


@public.route('/forecasting',methods=['post','get'])
def forecasting():
    data={}
    rr=gru_aave()
    x=[]
    y=[]
    for i in range(0,len(rr)):
        x.append(i+1)
        y.append(rr[i][0])
    data['x']=x
    data['y']=y
    return render_template('forecasting.html',data=data)