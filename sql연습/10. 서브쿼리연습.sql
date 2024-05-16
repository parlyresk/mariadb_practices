-- subquery

-- 1) select 절, insert into t1 values(...)

select (select 1+2 from dual) from dual;




-- 2) from 절의 서브쿼리

select now() as n,sysdate() as s, 3+1 as r from dual;

select * from (select now() as n,sysdate() as s, 3+1 as r from dual) as a;

-- 3) where 절의 서브쿼리

-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번 이름 출력
select b.dept_no
from employees a, dept_emp b
where a.emp_no=b.emp_no 
	and b.to_date='9999-01-01'
    and concat(a.first_name,' ',a.last_name)= 'Fai Bale';
    
select a.emp_no,a.first_name
from employees a,dept_emp b
where a.emp_no=b.emp_no
	and b.to_date='9999-01-01'
    and b.dept_no=(select b.dept_no
					from employees a, dept_emp b
					where a.emp_no=b.emp_no 
						and b.to_date='9999-01-01'
						and concat(a.first_name,' ',a.last_name)= 'Fai Bale');
                        
-- 3-1) 단일행 연산자: =,<,>,<>,<=,>=,!=
-- 실습문제1
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력
SELECT 
    e.first_name, s.salary
FROM
    employees e,
    salaries s
WHERE
    e.emp_no = s.emp_no
        AND s.salary < (SELECT 
            AVG(salary)
        FROM
            salaries
        WHERE
            to_date = '9999-01-01')
        AND s.to_date = '9999-01-01'
ORDER BY s.salary DESC;

-- 실습문제2
-- 현재, 직책별 평균급여 중에 가장 작은 직책의 직책과 그 평균 급여를 출력

select t.title,avg(s.salary)
from salaries s,titles t
where s.emp_no=t.emp_no and s.to_date='9999-01-01' and t.to_date='9999-01-01'
group by t.title
order by avg(s.salary) asc limit 1;


    
SELECT 
    t.title, AVG(s.salary) AS avg_salary
FROM
    salaries s,
    titles t
WHERE
    s.emp_no = t.emp_no
        AND s.to_date = '9999-01-01'
        AND t.to_date = '9999-01-01'
GROUP BY t.title
HAVING AVG(s.salary) = (SELECT 
        MIN(avg_salary)
    FROM
        (SELECT 
            AVG(s.salary) AS avg_salary
        FROM
            salaries s, titles t
        WHERE
            s.emp_no = t.emp_no
                AND s.to_date = '9999-01-01'
                AND t.to_date = '9999-01-01'
        GROUP BY t.title) sub);
 


-- 3-2) 복수행 연산자: in,not in, 비교연산자가 붙은 any,비교연산자가 붙은 all,

-- any 사용법
-- 1. =any: in
-- 2. >any >=any: 최소값
-- 3. <any, <=any: 최대값
-- 4. <>any , !=any: not in

-- all 사용법
-- 1. =all: (가능은 하나 의미 없는 문법)
-- 2. >all, >=all: 최대값
-- 3. <all, <=all: 최소값
-- 4. <>all, !=all

-- 실습문제 3
-- 현재 급여가 50,000 이상인 직원의 이름과 급여를 출력하세요




-- sol1) join

select e.first_name,s.salary
from employees e,salaries s
where e.emp_no=s.emp_no and s.salary>=50000 and s.to_date='9999-01-01';

-- sol2) subquery where(in)

select emp_no,salary
from salaries
where to_date='9999-01-01'
	and salary>=50000;
    
SELECT 
    e.first_name, s.salary
FROM
    employees e,
    salaries s
WHERE
    e.emp_no = s.emp_no
        AND s.to_date = '9999-01-01'
        AND (e.emp_no , s.salary) IN (SELECT 
            emp_no, salary
        FROM
            salaries
        WHERE
            to_date = '9999-01-01'
                AND salary >= 50000);
                
-- 실습문제4
-- 현재, 각 부서별  최고 급여를 받고 있는 직원의 이름과 월급
SELECT 
    e.first_name, MAX(s.salary), d.dept_name
FROM
    employees e,
    salaries s,
    departments d,
    dept_emp de
WHERE
    e.emp_no = de.emp_no
        AND e.emp_no = s.emp_no
        AND d.dept_no = de.dept_no
        AND s.to_date = '9999-01-01'
        AND de.to_date = '9999-01-01'
        AND (s.salary , d.dept_name) IN (SELECT 
            MAX(s.salary), d.dept_name
        FROM
            
            salaries s,
            departments d,
            dept_emp de
        WHERE
            
                de.emp_no=s.emp_no
                AND d.dept_no = de.dept_no
                AND s.to_date = '9999-01-01'
                AND de.to_date = '9999-01-01'
        GROUP BY d.dept_name)
GROUP BY d.dept_name;


select e.first_name,max(s.salary),d.dept_name
from employees e, salaries s,departments d,dept_emp de
where e.emp_no=de.emp_no and e.emp_no=s.emp_no and d.dept_no=de.dept_no
	and s.to_date='9999-01-01' and de.to_date='9999-01-01'
group by d.dept_name;
    