-- inner join

-- 예제1) 현재 근무하고 있는 직원의 이름과 직책 모두 출력

select a.first_name,b.title
from employees a,titles b
where a.emp_no=b.emp_no and b.to_date='9999-01-01';

-- 예제2) 현재 근무하고 있는 직원의 이름과 직책 모두 출력하되, 여성 엔지니어만 출력

select a.first_name,b.title
from employees a,titles b
where a.emp_no=b.emp_no 
	and b.to_date='9999-01-01' 
	and a.gender='f'
    and b.title='engineer';
    

-- ANSI/ISO SQL1999 Join 표준문법

-- 1) natural join
-- 조인 대상이 되는 두 테이블에 이름이 같은 공통 컬럼이 있으면 
-- 조인 조건을 명시하지 않고 암묵적으로 조인이 된다

select a.first_name,b.title
from employees a natural join titles b 
where b.to_date='9999-01-01';


-- 2) join ~ using
--    natural join 문제점

select count(*)
from salaries s join titles t using(emp_no)
where t.to_date='9999-01-01' and s.to_date='9999-01-01';


-- 3) join ~ on
-- 예제) 현재, 직책별 평균 연봉을 큰 순서대로 출력하세요

select t.title,avg(s.salary)
from salaries s join titles t on s.emp_no=t.emp_no
where s.to_date='9999-01-01' and t.to_date='9999-01-01'
group by t.title
order by avg(s.salary) desc;



-- 실습문제1
-- 현재 직원별 근무 부서를 출력해보세요
-- 사번,직원이름(first_name),부서명을 출력

select e.emp_no,e.first_name,d.dept_name
from employees e,dept_emp de,departments d
where e.emp_no=de.emp_no 
	and de.dept_no=d.dept_no
	and de.to_date='9999-01-01';


-- 실습문제2
-- 현재 지급되고 있는 급여 출력
-- 사번,직원이름(first_name),급여를 출력

select e.emp_no,e.first_name,s.salary
from employees e, salaries s
where e.emp_no=s.emp_no and
	s.to_date='9999-01-01';
    
-- 실습문제3
-- 현재, 직책별 평균연봉과 직원 수를 구하되 직원수가 100명 이상인 직책

select t.title,avg(s.salary),count(*)
from salaries s join titles t on s.emp_no=t.emp_no
where s.to_date='9999-01-01' and t.to_date='9999-01-01'
group by t.title
having count(*)>=100;


-- 실습문제4
-- 현재, 부서별로 직책이 Engineer인 직원들에 대해서만 평균 연봉 구하기

select d.dept_name,avg(s.salary)
from dept_emp de join salaries s on de.emp_no=s.emp_no
join titles t on t.emp_no=s.emp_no
join departments d on d.dept_no=de.dept_no
where t.title='Engineer' 
	and t.to_date='9999-01-01' 
	and s.to_date='9999-01-01' 
	and de.to_date='9999-01-01'
group by d.dept_name;

