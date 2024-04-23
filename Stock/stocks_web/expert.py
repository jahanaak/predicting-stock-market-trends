from flask import *
from database import *


expert=Blueprint('expert',__name__)

@expert.route('/expert_home')
def expert_home():
	return render_template('expert_home.html')


@expert.route('/notification',methods=['post','get'])
def notification():
	data={}
	if 'ns' in request.form:
		noti=request.form['noti']
		
		q="insert into notification values(null,'%s','%s',curdate(),'pending')"%(session['eid'],noti)
		insert(q)
	q="select * from notification where expert_id='%s'"%(session['eid'])
	data['view']=select(q)

	if 'action' in request.args:
		action=request.args['action']
		nid=request.args['nid']
	else:
		action=None

	if action=='update':
		q="select * from notification where notification_id='%s'"%(nid)
		data['up']=select(q)

		if 'update' in request.form:
			noti=request.form['noti']
			q="update notification set notification='%s'  where notification_id='%s'"%(noti,nid)
			update(q)
			return redirect(url_for('expert.expert_home'))
	
	if action=='delete':
		q="delete from notification where notification_id='%s'"%(nid )
		delete(q)
		return redirect(url_for('expert.expert_home'))
	return render_template('notification.html',data=data)

@expert.route('/tips',methods=['post','get'])
def tips():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		tid=request.args['tid']
	else:
		action=None
	if 'ts' in request.form:
		ti=request.form['ti']
		qg="insert into tips values(null,'%s','%s',now())"%(session['eid'],ti)
		insert(qg)

	if action=='update':
		q="select * from tips where tips_id='%s'"%(tid)
		data['up']=select(q)
		if 'update' in request.form:
			ti=request.form['ti']
			da=request.form['da']
			q="update tips set tips='%s',date='%s' where tips_id='%s'"%(ti,da,tid)
			update(q)
			return redirect(url_for('expert.expert_home'))

	q1="select * from tips where expert_id='%s'"%(session['eid'])
	data['view']=select(q1)

	if action=='delete':
		q="delete from tips where tips_id='%s'"%(tid)
		delete(q)
		return redirect(url_for('expert.expert_home'))
	return render_template('tips.html',data=data)

@expert.route('/expert_view_doubts')
def expert_view_doubts():
	data={}
	q="select * from doubt where expert_id='%s'"%(session['eid'])
	data['view']=select(q)
	return render_template('expert_view_doubts.html',data=data)

@expert.route('/expert_reply',methods=['post','get'])
def expert_reply():
	if 'rs' in request.form:
		rep=request.form['rep']
		did=request.args['did']
		q="update doubt set reply='%s' where doubt_id='%s'"%(rep,did)
		update(q)
	return render_template('expert_reply.html')

@expert.route('/expert_view_rating')
def expert_view_rating():
	data={}
	s="select * from rating inner join user using(user_id)"
	data['view']=select(s)
	return render_template('expert_view_rating.html',data=data)