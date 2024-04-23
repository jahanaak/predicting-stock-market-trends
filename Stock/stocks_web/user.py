from flask import *
from database import *


user=Blueprint('user',__name__)


@user.route('/user_home')
def user_home():
	return render_template('user_home.html')

@user.route('/user_complaint',methods=['post','get'])
def user_complaint():
	
	if 'cs' in request.form:
		comp=request.form['comp']
		q="insert into complaint values(null,'%s','%s','pending',curdate())"%(session['uid'],comp)
		insert(q)
	return render_template('user_complaint.html')
	


@user.route('/doubt',methods=['post','get'])
def doubt():
	
	if 'ds' in request.form:
		eid=request.args['eid']
		dou=request.form['dou']
		q="insert into doubt values(null,'%s','%s','%s','pending',now())"%(session['uid'],eid,dou)
		insert(q)
	return render_template('doubt.html')


@user.route('/user_view_tips')
def user_view_tips():
	data={}
	q="select * from tips"
	data['view']=select(q)
	return render_template('user_view_tips.html',data=data)

@user.route('/user_view_notification')
def user_view_notification():
	data={}
	q="select * from notification where expert_id='%s'"%(session['eid'])
	data['view']=select(q)
	return render_template('user_view_notification.html',data=data)

@user.route('/user_view_reply')
def user_view_reply():
	data={}
	q="select * from complaint where user_id='%s'"%(session['uid'])
	data['view']=select(q)
	return render_template('user_view_reply.html',data=data)

@user.route('/user_add_rating',methods=['post','get'])
def user_add_rating():
	if 'rat' in request.form:
		rate=request.form['rate']
		rev=request.form['rev']
		eid=request.args['eid']
		q="insert into rating values(null,'%s','%s','%s','%s',now())"%(session['uid'],eid,rate,rev)
		insert(q)
	return render_template('user_add_rating.html')

