from flask import *
from database import *
from gru_python_aave import *
import joblib

api=Blueprint('api',__name__)

@api.route("/login_here",methods=["post"])
def login_here():
      
   username=request.form['username']
   password=request.form['password']
   Q="select * from slogin where username='%s' and password='%s'"%(username,password)
   res=select(Q)
   if res:
         if res[0]['usertype']=="user":
            return jsonify(status="ok",lid=res[0]['login_id'])
         else:
            return jsonify(status="failed")
   else:
      return jsonify(status="failed")
  
  
  

@api.route("/signup",methods=["post"])
def signup():
   fn=request.form['fname']
   ln=request.form['lname']
   pl=request.form['place']
   pi=request.form['pin']
   po=request.form['post']
   ph=request.form['phone']
   em=request.form['email']
   username=request.form['username']      
   password=request.form['password']
   type=request.form['type']
   q="insert into slogin values(null,'%s','%s','user')"%(username,password)
   rid=insert(q)
   w="insert into user values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(rid,fn,ln,pl,po,pi,ph,em,type)
   res=insert(w)
   print()
   if res:   
      return jsonify(status="ok" )   
   else:
      return jsonify(status="failed")
  
  
@api.route('/v_notif',methods=['post'])
def v_notif():
   us="select * from notification "
   les=select(us)
   print(les,'aaaaaaaaaaaaaaaaaaa')
   if les:   
      return jsonify(status="ok" ,data=les)   
   else:
      return jsonify(status="failed")
  
  
@api.route('/v_tips',methods=['post'])
def v_tips():
   us="SELECT *,CONCAT (firstname,' ',lastname)AS ename FROM `tips` INNER JOIN expert USING (expert_id) "
   exp=select(us)
   print(exp,'bbbbbbbbbbbbbbbbbbb')
   if exp:   
      return jsonify(status="ok" ,data=exp)   
   else:
      return jsonify(status="failed")
   

@api.route('/v_experts',methods=['post'])
def v_experts():
   us="SELECT *from expert"
   exp=select(us)
   print(exp,'cccccccccccc')
   if exp:   
      return jsonify(status="ok" ,data=exp)   
   else:
      return jsonify(status="failed")
   
@api.route("/a_doubts",methods=["post"])
def a_doubts():
   lid=request.form['login_id']
   eid=request.form['exp_id']
   fn=request.form['dbt']
   
   w="insert into doubt values(null,(select user_id from user where login_id='%s'),'%s','%s','pending',curdate())"%(lid,eid,fn)
   res=insert(w)
   print()
   if res:   
      return jsonify(status="ok" )   
   else:
      return jsonify(status="failed")
   
   
  
@api.route('/v_dbt',methods=['post'])
def v_dbt():
   lid=request.form['login_id']
   
   us="SELECT * from doubt where user_id=(select user_id from user where login_id='%s')"%(lid)
   exp=select(us)
   print(exp,'dddddddd')
   if exp:   
      return jsonify(status="ok" ,data=exp)   
   else:
      return jsonify(status="failed")
   
@api.route('/ua_cmp',methods=['post'])
def a_cmp():
   lid=request.form['login_id']
   cmpl=request.form['cmpl']
   us="insert into complaint values(null,(select user_id from user where login_id='%s'),'%s','pending',curdate())"%(lid,cmpl)
   cm=insert(us)
   print(cm,'eeeeeeeee')
   if cm:   
      return jsonify(status="ok" ,data=cm)   
   else:
      return jsonify(status="failed")

   
@api.route('/uv_cmp',methods=['post'])
def v_cmp():
   lid=request.form['login_id']
   
   us="SELECT * from complaint where user_id=(select user_id from user where login_id='%s')"%(lid)
   exp=select(us)
   print(exp,'dddddddd')
   if exp:   
      return jsonify(status="ok" ,data=exp)   
   else:
      return jsonify(status="failed")
   
   
@api.route('/rate_e',methods=['get','post'])
def rate_e():
   data={}
   login_id=request.args['lid']
   exp_id=request.args['exp_id']
   rate=request.args['rating']
   review=request.args['review']

   q="select * from user where login_id='%s'"%(login_id)
   res=select(q)
   q="select * from rating where user_id='%s' and expert_id='%s'"%(res[0]['user_id'],exp_id)
   res1=select(q)
   if res1:
      q="update rating set rating='%s',review='%s' where rating_id='%s'"%(rate,review,res1[0]['rating_id'])
      id=update(q)
      data['status']="success"
      data['method']="Review"

   else:

      q="insert into rating values(NULL,'%s','%s','%s','%s',now())"%(res[0]['user_id'],exp_id,rate,review)
      id=insert(q)
   if id:
      data['status']="success"
      data['method']="Review"
      data['data']=id
      
   else:
      data['status']="failed"
      data['method']="Review"
   return (data)



@api.route('/ViewReview',methods=['get','post'])
def ViewReview():
	data={}
	login_id=request.args['lid']
	exp_id=request.args['exp_id']
	q="select * from user where login_id='%s'"%(login_id)
	res=select(q)
	q="select * from rating inner join expert using(expert_id) where expert_id='%s' and user_id='%s' "%(exp_id,res[0]['user_id'])
	res=select(q)
	if res:
		data['status']="success"
		data['method']="ViewReview"
		data['data']=res[0]['rating']
		data['data1']=res[0]['review']
   
	else:
		data['status']="failed"
		data['method']="ViewReview"

	return (data)

@api.route('/predicting',methods=['get','post'])
def predicting():
    data={}
    rr=gru_aave()
    x=[]
    y=[]
    
    for i in range(0,len(rr)):
        x.append(i+1)
        y.append(rr[i][0])
        # ss={"x":i+1,"y":rr[i][0]}
        # xx.append(ss)
    # print(xx)
    # data['xx']=xx
    data['status']="success"
    data['x']=x
    data['y']=y    
    data['method']="predicting"

    return str(data)
    # return render(request,'foercasting.html',{"x":x,"y":y})


# 	# return demjson.encode(data)


@api.route('/api_viewingraph',methods=['post','get'])
def api_viewingraph():
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


@api.route('/predictout',methods=['post','get'])
def predictout():
    data={}
    q="select * from stockdetails order by stocks_id desc"
    res=select(q)
    a=res[0]['open']
    b=res[0]['high']
    c=res[0]['low']
    d=res[0]['close']
    # Loading the model from disk
    loaded_model = joblib.load('decision_tree_model.pkl')

    # Example prediction for new data
    new_data = [[a, b, c, d]]
    prediction_new = loaded_model.predict(new_data)
    print("Predicted Volume for new data point:", prediction_new[0])
    q="insert into predicts values(null,'%s','%s','%s','%s','%s')" %(a,b,c,d,prediction_new[0])
    insert(q)
    # return render_template('forecasting.html',data=data)
     
    return jsonify(status="ok" ,data=prediction_new[0],method="predictout")   
    
    return str(prediction_new[0])




@api.route('/viewout',methods=['post'])
def viewout():
   
   us="SELECT * from predicts order by desc"
   exp=select(us)
   
   print(exp,'dddddddd')

   if exp:   
      return jsonify(status="ok" ,data=exp[0]['out'],method=exp[0]['out'])   
   else:
      return jsonify(status="failed")
   

