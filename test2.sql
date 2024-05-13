drop table if exists CLASS_NUM;
drop table if exists SCHOOL;
drop table if exists STUDENT;
drop table if exists SUBJECT;
drop table if exists TEACHER;
drop table if exists TEST;

-- 学校
create table SCHOOL (
    CD char(3) primary key,
    NAME varchar(20) default null
);

-- クラス番号
create table CLASS_NUM (
    SCHOOL_CD char(3),
    CLASS_NUM varchar(5),
    primary key (SCHOOL_CD, CLASS_NUM),
    foreign key (SCHOOL_CD) references SCHOOL(CD)
);

-- 学生
create table STUDENT (
    NO varchar(10) primary key,
    NAME varchar(10) default null,
    ENT_YEAR integer default null,
    CLASS_NUM char(3) default null,
    IS_ATTEND boolean default null,
    SCHOOL_CD char(3) default null,
    foreign key (CLASS_NUM) references CLASS_NUM(CLASS_NUM),
    foreign key (SCHOOL_CD) references SCHOOL(CD)
);

-- 科目
create table SUBJECT (
    SCHOOL_CD char(3),
    CD char(3),
    NAME varchar(20) default null,
    DELETED boolean default false,
    primary key (SCHOOL_CD, CD),
    foreign key (SCHOOL_CD) references SCHOOL(CD)
);

-- 教員
create table TEACHER (
    ID varchar(10) primary key,
    PASSWORD varchar(30) default null,
    NAME varchar(10) default null,
    SCHOOL_CD char(3) default null,
    foreign key (SCHOOL_CD) references SCHOOL(CD)
);

-- 得点
create table TEST (
    STUDENT_NO varchar(10),
    SUBJECT_CD char(3),
    SCHOOL_CD char(3),
    NO integer,
    POINT integer default null,
    CLASS_NUM varchar(5) default null,
    DELETED boolean default false,
    primary key (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO),
    foreign key (STUDENT_NO) references STUDENT(NO),
    foreign key (SUBJECT_CD, SCHOOL_CD) references SUBJECT(CD, SCHOOL_CD),
    foreign key (CLASS_NUM) references CLASS_NUM(CLASS_NUM)
);


-- **************************************
-- 以下、サンプルデータ
-- **************************************

-- 学校
insert into SCHOOL (CD, NAME) values
('oom', '大宮校'),
('tky', '東京校');

-- 教員
insert into TEACHER (ID, PASSWORD, NAME, SCHOOL_CD) values
('admin', 'password', '大原太郎', 'oom'),
('admin2', 'password2', '大原次郎', 'oom'),
('admin3', 'password3', '大原三郎', 'oom'),
('admin4', 'password4', '東京太郎', 'tky');

-- クラス番号
insert into CLASS_NUM (SCHOOL_CD, CLASS_NUM) values
('oom', '101'),
('oom', '102'),
('tky', '201'),
('tky', '202');

-- 学生
insert into STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) values
('001', '山田太郎', 2019, '101', true, 'oom'),
('002', '田中花子', 2020, '101', false, 'oom'),
('003', '鈴木一郎', 2019, '101', true, 'oom'),
('004', '佐藤美和', 2020, '101', false, 'oom'),
('005', '高橋健太', 2019, '101', true, 'oom'),
('006', '伊藤真理子', 2020, '102', false, 'oom'),
('007', '渡辺拓也', 2019, '102', true, 'oom'),
('008', '中村理恵', 2020, '102', false, 'oom'),
('009', '小林浩司', 2019, '102', true, 'oom'),
('010', '加藤さやか', 2020, '102', false, 'oom'),
('011', '林大介', 2019, '201', true, 'tky'),
('012', '山本麻美', 2020, '201', false, 'tky'),
('013', '木村啓太', 2019, '201', true, 'tky'),
('014', '斉藤美咲', 2020, '201', false, 'tky'),
('015', '松本悠太', 2019, '201', true, 'tky'),
('016', '佐々木真理', 2020, '202', false, 'tky'),
('017', '遠藤直人', 2019, '202', true, 'tky'),
('018', '田村絵美', 2020, '202', false, 'tky'),
('019', '岡田健太郎', 2019, '202', true, 'tky'),
('020', '中島由美', 2020, '202', false, 'tky');

-- 科目
insert into SUBJECT (SCHOOL_CD, CD, NAME) values
('oom', '001', '国語'),
('oom', '002', '数学'),
('oom', '003', '物理'),
('tky', '004', '英語'),
('tky', '005', '歴史');

-- 得点
insert into TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) values
('001', '001', 'oom', 1, 85, '101'),
('001', '001', 'oom', 2, 30, '101'),
('001', '002', 'oom', 1, 90, '101'),
('001', '002', 'oom', 2, 100, '101'),
('001', '003', 'oom', 1, 2, '101'),
('001', '003', 'oom', 2, 49, '101'),
('002', '001', 'oom', 1, 75, '101'),
('002', '001', 'oom', 2, 83, '101'),
('002', '002', 'oom', 1, 80, '101'),
('002', '002', 'oom', 2, 80, '101'),
('003', '001', 'oom', 1, 95, '101'),
('003', '001', 'oom', 2, 77, '101'),
('003', '002', 'oom', 1, 0, '101'),
('003', '002', 'oom', 2, 13, '101'),
('004', '001', 'oom', 1, 88, '101'),
('004', '001', 'oom', 2, 92, '101'),
('004', '002', 'oom', 1, 78, '101'),
('004', '002', 'oom', 2, 85, '101'),
('005', '001', 'oom', 1, 79, '101'),
('005', '001', 'oom', 2, 81, '101'),
('005', '002', 'oom', 1, 63, '101'),
('005', '002', 'oom', 2, 90, '101'),
('006', '001', 'oom', 1, 94, '102'),
('006', '001', 'oom', 2, 99, '102'),
('006', '002', 'oom', 1, 82, '102'),
('006', '002', 'oom', 2, 75, '102'),
('007', '001', 'oom', 1, 86, '102'),
('007', '001', 'oom', 2, 3, '102'),
('007', '002', 'oom', 1, 91, '102'),
('007', '002', 'oom', 2, 83, '102'),
('008', '001', 'oom', 1, 92, '102'),
('008', '001', 'oom', 2, 24, '102'),
('008', '002', 'oom', 1, 75, '102'),
('008', '002', 'oom', 2, 80, '102'),
('009', '001', 'oom', 1, 85, '102'),
('009', '001', 'oom', 2, 90, '102'),
('009', '002', 'oom', 1, 86, '102'),
('009', '002', 'oom', 2, 78, '102'),
('010', '001', 'oom', 1, 93, '102'),
('010', '001', 'oom', 2, 91, '102'),
('010', '002', 'oom', 1, 84, '102'),
('010', '002', 'oom', 2, 97, '102'),
('011', '004', 'tky', 1, 88, '201'),
('011', '005', 'tky', 2, 92, '201'),
('012', '004', 'tky', 1, 68, '201'),
('012', '005', 'tky', 2, 70, '201'),
('013', '004', 'tky', 1, 82, '201'),
('013', '005', 'tky', 2, 85, '201'),
('014', '004', 'tky', 1, 75, '201'),
('014', '005', 'tky', 2, 78, '201'),
('015', '004', 'tky', 1, 88, '201'),
('015', '005', 'tky', 2, 92, '201'),
('016', '004', 'tky', 1, 68, '202'),
('016', '005', 'tky', 2, 70, '202'),
('017', '004', 'tky', 1, 82, '202'),
('017', '005', 'tky', 2, 85, '202'),
('018', '004', 'tky', 1, 75, '202'),
('018', '005', 'tky', 2, 78, '202'),
('019', '004', 'tky', 1, 88, '202'),
('019', '005', 'tky', 2, 92, '202'),
('020', '004', 'tky', 1, 68, '202'),
('020', '005', 'tky', 2, 70, '202'),