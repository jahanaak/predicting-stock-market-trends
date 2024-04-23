from flask import *
from database import *

admin=Blueprint('admin',__name__)


@admin.route('/admin_home')
def admin_home():
	return render_template('admin_home.html')

@admin.route('/admin_view_notification')
def admin_view_notification():
	data={}
	q="select *,concat(firstname,' ',lastname)as name from notification inner join expert using(expert_id)"
	data['view']=select(q)
	return render_template('admin_view_notification.html',data=data)

@admin.route('/admin_view_complaint')
def admin_view_complaint():
	data={}

	q="select *,concat(firstname,' ',lastname)as name from complaint inner join user using(user_id)"
	data['view']=select(q)
	return render_template('admin_view_complaint.html',data=data)

@admin.route('/admin_reply',methods=['post','get'])
def admin_reply():
	if 'rs' in request.form:
		cid=request.args['cid']
		rep=request.form['rep']
		q="update complaint set reply='%s' where complaint_id='%s'"%(rep,cid)
		update(q)
		return redirect(url_for('admin.admin_home'))
	return render_template('admin_reply.html')

@admin.route('/admin_manage_expert',methods=['post','get'])
def admin_manage_expert():
	data={}
	if 'es' in request.form:
		fname=request.form['fname']
		lname=request.form['lname']
		place=request.form['place']
		po=request.form['po']
		pi=request.form['pi']
		phn=request.form['phn']
		mail=request.form['mail']
		uname=request.form['uname']
		passw=request.form['passw']
		q="insert into slogin values(null,'%s','%s','expert')"%(uname,passw)
		res=insert(q)
		q1="insert into expert values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(res,fname,lname,place,po,pi,phn,mail)
		insert(q1)
	if 'action' in request.args:
		action=request.args['action']
		eid=request.args['eid']
	else:
		action=None
	if action=='update':
		q="select * from expert where expert_id='%s'"%(eid)
		data['up']=select(q)
		if'update' in request.form:
			fname=request.form['fname']
			lname=request.form['lname']
			place=request.form['place']
			po=request.form['po']
			pi=request.form['pi']
			phn=request.form['phn']
			mail=request.form['mail']
			
			q="update expert set firstname='%s',lastname='%s',place='%s',post='%s',pin='%s',phone='%s',email='%s'where expert_id='%s'"%(fname,lname,place,po,pi,phn,mail,eid)
			update(q)
			# q1="update slogin set username='%s',password='%s' where login_id='%s'"%(session['lid']) 
			# update(q1)
			return redirect(url_for('admin.admin_home'))

	if action=='delete':
		q="delete from expert where expert_id='%s'"%(eid)
		delete(q)
		return redirect(url_for('admin.admin_home'))
	
	q="select * from expert"
	data['view']=select(q)
	return render_template('admin_manage_expert.html',data=data)


@admin.route('/admin_view_rating')
def admin_view_rating():
	data={}
	s="select * from rating inner join `user` using(user_id)"
	data['view']=select(s)
	
	return render_template('admin_view_rating.html',data=data)

@admin.route('/adminadddetails',methods=['post','get'])
def adminadddetails():
	data={}
	if 'submit' in request.form:
		opens=request.form['open']
		high=request.form['high']
		low=request.form['low']
		close=request.form['close']
		
		q="insert into stockdetails values(null,'%s','%s','%s','%s')"%(opens,high,low,close)
		insert(q)
		
	
	q="select * from stockdetails order by stocks_id desc"
	data['view']=select(q)
	return render_template('adminadddetails.html',data=data)