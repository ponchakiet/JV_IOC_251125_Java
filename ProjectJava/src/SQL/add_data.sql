SET search_path TO quanlykhoahoc;
INSERT INTO admin (username, password)
VALUES ('admin_kiet', '$2a$12$IxpdcepYxkwZL31tYELCD.OGLfX2KPop1MZrEsuo6GNFlY2WgX7Au');


INSERT INTO student (name, dob, email, sex, phone, password) VALUES
                                                                 ('Nguyễn Văn An', '2005-01-15', 'an.nv@gmail.com', true, '0901234567', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Trần Thị Bình', '2005-05-20', 'binh.tt@gmail.com', false, '0902345678', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Lê Hoàng Cường', '2004-12-30', 'cuong.lh@gmail.com', true, '0903456789', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Phạm Minh Đạo', '2005-03-10', 'dao.pm@gmail.com', true, '0904567890', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Đỗ Thanh Bình', '2005-11-25', 'binh.dt@gmail.com', false, '0905678901', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Hoàng Gia Hưng', '2004-07-08', 'hung.gh@gmail.com', true, '0906789012', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Vũ Thùy Linh', '2005-09-12', 'linh.vt@gmail.com', false, '0907890123', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Bùi Anh Tuấn', '2005-02-14', 'tuan.ba@gmail.com', true, '0908901234', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Ngô Phương Thảo', '2005-06-30', 'thao.np@gmail.com', false, '0909012345', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.'),
                                                                 ('Đặng Quốc Việt', '2004-10-05', 'viet.dq@gmail.com', true, '0910123456', '$2a$12$Wig/fMuy6Y./EPyqn3.yeucjMReMZ.WlSxmhSmjZ6tz6mqyzRh8/.');


INSERT INTO course (name, duration, instructor) VALUES
                                                    ('Lập trình Java Core', 60, 'Thầy Nguyễn Hữu Quang'),
                                                    ('Lập trình Web với React', 45, 'Cô Trần Thanh Thủy'),
                                                    ('Cấu trúc dữ liệu và Giải thuật', 50, 'Thầy Lê Minh Hùng'),
                                                    ('Cơ sở dữ liệu PostgreSQL', 40, 'Thầy Phạm Đức Long'),
                                                    ('Lập trình Python cơ bản', 30, 'Cô Đặng Thu Hà'),
                                                    ('An toàn thông tin mạng', 55, 'Thầy Trịnh Xuân Nam'),
                                                    ('Thiết kế UI/UX', 35, 'Cô Vũ Minh Anh'),
                                                    ('Phát triển Backend với Nodejs', 48, 'Thầy Bùi Quang Huy'),
                                                    ('Lập trình Mobile Flutter', 52, 'Thầy Hoàng Văn Đức'),
                                                    ('Machine Learning căn bản', 65, 'Cô Lê Phương Thảo');


INSERT INTO enrollment (student_id, course_id, status) VALUES
                                                           (12, 3, 'CONFIRM'),
                                                           (12, 4, 'WAITING'),
                                                           (12, 5, 'CONFIRM'),
                                                           (12, 6, 'DENIED'),
                                                           (12, 7, 'CANCEL'),
                                                           (6, 6, 'WAITING'),
                                                           (7, 8, 'CONFIRM'),
                                                           (8, 10, 'CONFIRM'),
                                                           (9, 4, 'WAITING'),
                                                           (10, 9, 'CONFIRM');