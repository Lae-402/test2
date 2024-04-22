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
    foreign key (SCHOOL_CD) references school(CD)
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
    cd char(3),
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
    foreign key (SCHOOL_CD) references SCHOOL(cd)
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
('001', '第一学校'),
('002', '第二学校');

-- クラス番号
insert into CLASS_NUM (SCHOOL_CD, CLASS_NUM) values
('001', 'A'),
('001', 'B'),
('001', 'C'),
('002', 'X'),
('002', 'Y'),
('002', 'Z');

-- 学生
insert into STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) values
('001', '田中太郎', 2020, 'A', true, '001'),
('002', '山田花子', 2021, 'B', true, '001'),
('003', '佐藤次郎', 2020, 'C', true, '001'),
('004', '鈴木三郎', 2021, 'X', true, '002'),
('005', '高橋直美', 2020, 'Y', true, '002'),
('006', '伊藤雅子', 2021, 'Z', true, '002'),
('007', '渡辺健太', 2020, 'A', false, '001'),
('008', '中村芳子', 2021, 'B', false, '001'),
('009', '小林健一', 2020, 'C', false, '001'),
('010', '斎藤美穂', 2021, 'X', false, '002'),
('011', '加藤隆', 2020, 'Y', false, '002'),
('012', '山口真理', 2021, 'Z', false, '002'),
('013', '吉田健司', 2020, 'A', true, '001'),
('014', '田村悠子', 2021, 'B', true, '001'),
('015', '岡田達也', 2020, 'C', true, '001'),
('016', '金子優子', 2021, 'X', true, '002'),
('017', '佐々木一郎', 2020, 'Y', true, '002'),
('018', '青木麻衣', 2021, 'Z', true, '002'),
('019', '松本秀樹', 2020, 'A', true, '001'),
('020', '山本美智子', 2021, 'B', true, '001'),
('021', '中野貴之', 2020, 'C', true, '001'),
('022', '前田真紀子', 2021, 'X', true, '002'),
('023', '後藤健二', 2020, 'Y', true, '002'),
('024', '片山麗子', 2021, 'Z', true, '002'),
('025', '水野修一', 2020, 'A', true, '001'),
('026', '加藤智子', 2021, 'B', true, '001'),
('027', '山田健介', 2020, 'C', true, '001'),
('028', '木村奈美', 2021, 'X', true, '002'),
('029', '小川真紀', 2020, 'Y', true, '002'),
('030', '鈴木健司', 2021, 'Z', true, '002'),
('031', '西村悟', 2020, 'A', false, '001'),
('032', '岩田さゆり', 2021, 'B', false, '001'),
('033', '渡部悠', 2020, 'C', false, '001'),
('034', '宮本大輔', 2021, 'X', false, '002'),
('035', '長谷川美咲', 2020, 'Y', false, '002'),
('036', '田辺健太', 2021, 'Z', false, '002'),
('037', '杉山聡', 2020, 'A', true, '001'),
('038', '星野美和', 2021, 'B', true, '001'),
('039', '大塚幸子', 2020, 'C', true, '001'),
('040', '野村健太', 2021, 'X', true, '002'),
('041', '井上千代', 2020, 'Y', true, '002'),
('042', '横山隆', 2021, 'Z', true, '002'),
('043', '菅原真一', 2020, 'A', false, '001'),
('044', '工藤美和子', 2021, 'B', false, '001'),
('045', '本田拓也', 2020, 'C', false, '001'),
('046', '小島佳子', 2021, 'X', false, '002'),
('047', '平野秀樹', 2020, 'Y', false, '002'),
('048', '池田絵美', 2021, 'Z', false, '002'),
('049', '吉岡拓也', 2020, 'A', true, '001'),
('050', '今井美穂', 2021, 'B', true, '001'),
('051', '岡本健太', 2020, 'C', true, '001'),
('052', '村上綾', 2021, 'X', true, '002'),
('053', '加藤真理子', 2020, 'Y', true, '002'),
('054', '中田健太', 2021, 'Z', true, '002'),
('055', '高木奈美', 2020, 'A', true, '001'),
('056', '山口健太', 2021, 'B', true, '001'),
('057', '伊藤美穂', 2020, 'C', true, '001'),
('058', '清水拓也', 2021, 'X', true, '002'),
('059', '加藤麻美', 2020, 'Y', true, '002'),
('060', '田中健太郎', 2021, 'Z', true, '002');

-- 教員
insert into teacher (id, password, name, school_cd) values
('admin', 'password', '大原太郎', '001');

-- 科目
insert into SUBJECT (SCHOOL_CD, CD, NAME) values
('001', '001', '数学'),
('001', '002', '国語'),
('001', '003', '英語'),
('002', '001', '物理'),
('002', '002', '化学');

-- 得点
insert into TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) values
('001', '001', '001', 1, 85, 'A'),
('001', '002', '001', 1, 75, 'A'),
('001', '003', '001', 1, 80, 'A'),
('002', '001', '001', 2, 90, 'B'),
('002', '002', '001', 1, 70, 'B'),
('002', '003', '001', 2, 85, 'B'),
('003', '001', '001', 1, 80, 'C'),
('003', '002', '001', 1, 65, 'C'),
('003', '003', '001', 2, 75, 'C'),
('004', '001', '002', 1, 85, 'X'),
('004', '002', '002', 1, 75, 'X'),
('004', '003', '002', 2, 80, 'X'),
('005', '001', '002', 2, 90, 'Y'),
('005', '002', '002', 1, 70, 'Y'),
('005', '001', '002', 1, 85, 'Y'),
('006', '001', '002', 2, 80, 'Z'),
('006', '002', '002', 1, 65, 'Z'),
('006', '003', '002', 2, 75, 'Z');