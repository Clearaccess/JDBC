INSERT ALL
INTO Items (item_Id,seller_Id,title,description,start_price,start_bidding_date,buy_it_now) VALUES('1','1','title1','description1','100',TO_DATE('01.01.2017', 'DD.MM.YYYY'),'1')
INTO Items (item_Id,seller_Id,title,description,start_price,start_bidding_date,buy_it_now) VALUES('2','1','title2','description2','200',TO_DATE('01.01.2017', 'DD.MM.YYYY'),'1')
INTO Items (item_Id,seller_Id,title,description,start_price,start_bidding_date,buy_it_now) VALUES('3','2','title3','description3','300',TO_DATE('01.01.2017', 'DD.MM.YYYY'),'1')
INTO Items (item_Id,seller_Id,title,description,start_price,start_bidding_date,buy_it_now) VALUES('4','2','title4','description4','400',TO_DATE('01.01.2017', 'DD.MM.YYYY'),'1')
SELECT * FROM dual;