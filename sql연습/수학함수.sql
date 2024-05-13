
-- 수학함수

-- abs
select abs(1), abs(-1) from dual;

-- ceil
select ceil(3.14), ceil(-3.14), ceiling(3.14) from dual;

-- floor
select floor(3.14),floor(-3.14) from dual;

-- mod
select mod(10,3), 10%3 from dual;

-- round(x) x에 가장 근접한 정수
select round(1.34),round(1.5) from dual;

-- round(x,d) x값 중에 소수점 d 자리에 가장 근접한 실수
select round(1.498,1), round(1.498,2) from dual;

-- power(x,y), pow(x,y): x^y
select power(2,10),pow(2,5) from dual;

-- sign(x) : 양수 1, 음수 -1, 0이면 1
select sign(-1),sign(0),sign(1.1) from dual;

-- greates(x,y,...) least(x,y,...)
select greatest(10,40,20,50), least(10,20,40,50,60) from dual;

select greatest('A','B','Z'), least('a','B','z') from dual;

