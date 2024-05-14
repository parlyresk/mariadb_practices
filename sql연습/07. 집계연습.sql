-- 1) 집계쿼리: select 절에 통계함수(count,max,min,sum,avg,variance,stddev,...)
select avg(salary), sum(salary)
from salaries;

-- 2) select 절에 그룹함수(집계함수)가 있는 경우 어떤 칼럼도 select절에 올 수 없음
-- 예제의 emp_no은 가장 앞의 번호일 뿐 아무런 의미가 없음

select emp_no,avg(salary), sum(salary)
from salaries;

-- 3) query 순서
-- 1. from: 접근 테이블 확인
-- 2. where: 조건에 맞는 row를 선택(임시 테이블)
-- 3. 집계(결과테이블)
-- 4. projection
-- 예제) 사번이 10060인 사원이 받은 평균 월급
select avg(salary)
from salaries
where emp_no='10060';

-- 4) group by에 참여하고 있는 칼럼은 projection이 가능함
-- 예제: 사원별 평균 월급

select emp_no,avg(salary)
from salaries
group by emp_no;

-- 5) having
--    집계결과에서 (조건에 맞춰)row를 선택
--    이미 where절은 실행이 되었기 때문에 group by 이후 조건문은 having을 써야함
-- 예제) 평균 월급이 60000 달러 이상인 사원의 사번과 평균 월급을 출력 하세요
select emp_no,avg(salary)
from salaries
group by emp_no
having avg(salary)>=60000;

-- 6) order by
--  order by는 항상 맨 마지막에 출력함

select emp_no,avg(salary)
from salaries
group by emp_no
having avg(salary)>=60000
order by avg(salary);


-- 주의) 사번이 10060인 사원의 사번, 평균 월급, 급여 총합

-- 문법적으로 오류
-- 의미적으로는 맞긴 함
select emp_no,avg(salary),sum(salary)
from salaries
where emp_no=10060;

-- 문법적으로 옳다
select emp_no,avg(salary),sum(salary)
from salaries
group by emp_no
having emp_no=10060;






