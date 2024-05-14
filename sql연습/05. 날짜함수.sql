-- 날짜 함수

-- curdate(), current_date

select curdate(),current_date from dual;

-- curtime(),current)time
select curtime(), current_time from dual;

-- now() vs sysdate()
select now(),sysdate() from dual;
select now(),sleep(2),now() from dual;
select sysdate(),sleep(2),sysdate() from dual;

-- sysdate은 시스템이 시간이 지나는 시점을 고려하여 나오므로 쿼리의 시간을 재고 싶으면 사용해보기

-- date_format

select date_format(now(),'%Y년-%m월-%d일 %h시 %i분 %s초') from dual;
select date_format(now(),'%d %b \'%y') from dual;

-- period_diff
-- 예제) 근무개월
select first_name,
	hire_date,
    period_diff(date_format(curdate(),'%y%m'),date_format(hire_date,'%y%m')) as Month
from employees;

-- date_add(=adddate),date_sub(=subdate)
-- 날짜를 date 타입의 컬럼이나 값에 type(year,month,day)의 표현식으로 더하거나 뺌
-- 예제) 각 사원의 근속 연수가 5년이 되는 날에 휴가를 보내준다면 각 사원의 5년 근속 휴가 날짜는?
select first_name,hire_date,date_add(hire_date, interval 100 day)
from employees;

-- cast
select '12345'+10, cast('12345' as int) +10 from dual;
select date_format(cast('3011-01-01' as date),'%Y년 %m월 %d일') from dual;
select cast(cast(1-2 as unsigned) as signed) from dual;
select cast(cast(1-2 as unsigned) as int) from dual;
select cast(cast(1-2 as unsigned) as integer) from dual;

