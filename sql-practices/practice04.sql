-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?

select count(*)
from salaries
where to_date='9999-01-01'
	and salary>=(select avg(salary)
from salaries
where to_date='9999-01-01');



-- 문제2.
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.

select e.emp_no,e.first_name,d.dept_name,s.salary
from employees e,salaries s,departments d, dept_emp de
where e.emp_no=s.emp_no and d.dept_no=de.dept_no and de.emp_no=e.emp_no and
	s.to_date='9999-01-01' and de.to_date='9999-01-01' and (s.salary,de.dept_no) in
	(select max(s.salary),de.dept_no
from departments d,salaries s,dept_emp de
where d.dept_no=de.dept_no and
	s.emp_no=de.emp_no
    and s.to_date='9999-01-01'
    and de.to_date='9999-01-01'
group by de.dept_no)
order by s.salary desc;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요

select e.emp_no,e.first_name,s.salary
from employees e,dept_emp de,salaries s,(select de.dept_no de_no,avg(s.salary) avg_s
from dept_emp de,salaries s
where de.emp_no=s.emp_no and de.to_date='9999-01-01' and s.to_date='9999-01-01'
group by de.dept_no) sub
where e.emp_no=de.emp_no and s.emp_no=de.emp_no and de.to_date='9999-01-01' and
	s.to_date='9999-01-01' and s.salary>avg_s and de.dept_no=de_no;



-- 문제4.
-- 현재, 사원들의 사번, 이름, 자신의 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no,e.first_name,d.dept_name,sub.first_name 
from employees e,dept_emp de,departments d,(select dm.dept_no,e.first_name 
from dept_manager dm,employees e 
where dm.to_date='9999-01-01' and e.emp_no=dm.emp_no) sub 
where de.emp_no=e.emp_no and de.to_date='9999-01-01' and de.dept_no=sub.dept_no and d.dept_no=de.dept_no
order by e.emp_no;







-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select e.emp_no,e.first_name,t.title,s.salary
from employees e,titles t,salaries s,dept_emp de
where e.emp_no=t.emp_no and e.emp_no=s.emp_no and de.emp_no=e.emp_no 
	and s.to_date='9999-01-01' and t.to_date='9999-01-01' and de.to_date='9999-01-01' and
de.dept_no=(select de.dept_no
from salaries s,employees e,dept_emp de
where s.emp_no=e.emp_no and 
	e.emp_no=de.emp_no and 
    s.to_date='9999-01-01' and 
    de.to_date='9999-01-01'
group by de.dept_no
order by avg(salary) desc limit 1)
order by s.salary desc;



-- 문제6.
-- 평균 연봉이 가장 높은 부서는?
-- 부서 이름, 평균 연봉

select d.dept_name,avg(s.salary)
from departments d,dept_emp de,salaries s
where d.dept_no=de.dept_no and s.emp_no=de.emp_no
and d.dept_no=(select de.dept_no
from salaries s,employees e,dept_emp de
where s.emp_no=e.emp_no and 
	e.emp_no=de.emp_no
group by de.dept_no
order by avg(salary) desc limit 1);

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- 직책 이름, 평균 연봉
select t.title,avg(s.salary)
from titles t,salaries s
where t.emp_no=s.emp_no
group by t.title
order by avg(s.salary) desc limit 1;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

select d.dept_name as "부서이름" ,e.first_name as "사원이름",s.salary as "연봉", sub.first_name as "매니저이름",sub.salary as "매니저 연봉"
from employees e,dept_emp de,departments d,(select dm.dept_no,e.first_name,s.salary
from dept_manager dm,employees e,salaries s
where dm.to_date='9999-01-01' and e.emp_no=dm.emp_no and s.to_date='9999-01-01' and s.emp_no=dm.emp_no) sub,salaries s
where de.emp_no=e.emp_no and de.to_date='9999-01-01' and de.dept_no=sub.dept_no and d.dept_no=de.dept_no and s.emp_no=e.emp_no and s.to_date='9999-01-01'
and s.salary>sub.salary
order by e.emp_no;