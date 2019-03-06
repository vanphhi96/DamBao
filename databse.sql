
/****** Object:  Database [DiemSV]    Script Date: 03/06/2019 10:59:27 ******/
CREATE DATABASE [DiemSV]

USE DiemSV
GO

CREATE TABLE [dbo].[tblNienKhoa](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idChuyenNganh] [int] NOT NULL,
	[nienKhoa] [nvarchar](20) NOT NULL,
	[moTa] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblNienKhoa] ON
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (1, 1, N'D14', NULL)
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (2, 2, N'D14', NULL)
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (3, 3, N'D14', NULL)
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (5, 1, N'D15', N'')
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (6, 2, N'D15', NULL)
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (7, 3, N'D15', NULL)
INSERT [dbo].[tblNienKhoa] ([id], [idChuyenNganh], [nienKhoa], [moTa]) VALUES (8, 1, N'D16', NULL)
SET IDENTITY_INSERT [dbo].[tblNienKhoa] OFF
/****** Object:  Table [dbo].[tblMonNganh]    Script Date: 03/06/2019 10:59:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblMonNganh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idMon] [int] NOT NULL,
	[idChuyenNganh] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblMonNganh] ON
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (3, 208, 1)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (4, 209, 1)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (5, 210, 1)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (6, 211, 1)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (7, 212, 1)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (8, 213, 2)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (9, 214, 2)
INSERT [dbo].[tblMonNganh] ([id], [idMon], [idChuyenNganh]) VALUES (10, 215, 2)
SET IDENTITY_INSERT [dbo].[tblMonNganh] OFF
/****** Object:  Table [dbo].[tblSinhVien]    Script Date: 03/06/2019 10:59:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSinhVien](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idKhoaHoc] [int] NOT NULL,
	[tenSV] [nvarchar](255) NOT NULL,
	[ngaySinh] [date] NULL,
	[queQuan] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblSinhVien] ON
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (4, 1, N'Hoan Van An', CAST(0x521E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (5, 1, N'Le Tuan Anh', CAST(0x521E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (6, 1, N'Nguyen Duc Anh', CAST(0x521E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (8, 1, N'Hoang Viet Anh', CAST(0x521E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (9, 1, N'Nguyen Quy Chi', CAST(0x521E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (10, 1, N'Dang Bao Chien', CAST(0x521E0B00 AS Date), N'Hai Duong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (11, 2, N'Nguyen Van Chinh', CAST(0x521E0B00 AS Date), N'Ha Tay')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (12, 2, N'Nguyen Huu Cong', CAST(0x521E0B00 AS Date), N'Ha Tinh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (13, 2, N'Nguyen Van Dien', CAST(0x521E0B00 AS Date), N'Bac Giang')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (14, 2, N'Nguyen Duy', CAST(0x521E0B00 AS Date), N'Hung Yen')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (15, 2, N'Nguyen Quoc Duy', CAST(0x521E0B00 AS Date), N'Nam Dinh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (16, 2, N'Ta Anh Duy', CAST(0x521E0B00 AS Date), N'Viet Nam')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (17, 2, N'Do Tien Dat', CAST(0x521E0B00 AS Date), N'Viet Nam')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (138, 1, N' Hoàng Văn An ', CAST(0x721E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (139, 2, N' Lê Tuấn Anh ', CAST(0x731E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (140, 3, N' Nguyễn Đức Anh ', CAST(0x741E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (141, 5, N' Nguyễn Đức Anh ', CAST(0x751E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (142, 6, N' Nguyễn Hoàng Việt Anh ', CAST(0x761E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (143, 7, N' Nguyễn Quang Anh ', CAST(0x771E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (144, 8, N' Nguyễn Quý Chí ', CAST(0x781E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (145, 1, N' Đặng Bảo Chiến ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (146, 2, N' Nguyễn Văn Chính ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (147, 3, N' Nguyễn Hữu Công ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (148, 5, N' Nguyễn Văn Diện ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (149, 6, N' Nguyễn Duy ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (150, 7, N' Nguyễn Quốc Duy ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (151, 8, N' Tạ Anh Duy ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (152, 1, N' Đỗ Tiến Đạt ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (153, 2, N' Trần Tiến Đạt ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (154, 3, N' Nguyễn Văn Đông ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (155, 5, N' Phùng Trung Đức ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (156, 6, N' Vũ Tiến Hải ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (157, 7, N' Tô Ngọc Hiếu ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (158, 8, N' Vương Minh Hiếu ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (159, 1, N' Lê Tiến Hòa ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (160, 2, N' Nguyễn Trí Hoàng ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (161, 3, N' Nguyễn Quốc Hùng ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (162, 5, N' Nguyễn Quang Huy ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (163, 6, N' Trần Đình Huy ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (164, 7, N' Trần Duy Hưng ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (165, 1, N' Phan Thanh Liêm ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (166, 2, N' Nguyễn Thùy Linh ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (167, 3, N' Tạ Tài Linh ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (168, 5, N' Đào Duy Long ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (169, 6, N' Hoàng Long ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (170, 7, N' Nguyễn Hải Long ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (171, 8, N' Nguyễn Hoàng Long ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (172, 1, N' Đào Mạnh Luân ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (173, 2, N' Nguyễn Thế Lượng ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (174, 3, N' Phan Thanh Nguyên ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (175, 5, N' Lê Văn Phi ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (176, 6, N' Vũ Văn Phong ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (177, 7, N' Tạ Văn Quang ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (178, 8, N' Nguyễn Duy Quí ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (179, 1, N' Phạm Xuân Sang ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (180, 2, N' Ngô Thế Sơn ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (181, 3, N' Trần Văn Tân ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (182, 5, N' Lương Văn Thanh ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (183, 6, N' Tống Nguyên Thành ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (184, 7, N' Đỗ Đức Thắng ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (185, 8, N' Nguyễn Quốc Thịnh ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (186, 1, N' Đặng Văn Thuận ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (187, 2, N' Nguyễn Đức Trung ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (188, 3, N' Đào Tiến Trường ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (189, 5, N' Cấn Anh Tú ', CAST(0x791E0B00 AS Date), N'Hai Phong')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (190, 6, N' Đỗ Văn Tuấn ', CAST(0x791E0B00 AS Date), N'Quang Ninh')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (191, 7, N' Hoàng Anh Tuấn ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (192, 1, N' Nguyễn Anh Tuấn ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (193, 2, N' Mai Sơn Tùng ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (194, 3, N' Nguyễn Thanh Tùng ', CAST(0x791E0B00 AS Date), N'Thanh Hoa')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (195, 5, N' Đàm Trọng Việt ', CAST(0x791E0B00 AS Date), N'Nghe An')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (196, 6, N' Đặng Quốc Việt ', CAST(0x791E0B00 AS Date), N'Ha Noi')
INSERT [dbo].[tblSinhVien] ([id], [idKhoaHoc], [tenSV], [ngaySinh], [queQuan]) VALUES (197, 7, N' Lê Quang Vinh ', CAST(0x791E0B00 AS Date), N'Hai Phong')
SET IDENTITY_INSERT [dbo].[tblSinhVien] OFF
/****** Object:  Table [dbo].[tblMonKhoaHoc]    Script Date: 03/06/2019 10:59:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblMonKhoaHoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idMonChuyenNganh] [int] NOT NULL,
	[idNienKhoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblMonKhoaHoc] ON
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (2, 3, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (3, 4, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (4, 5, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (5, 6, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (6, 7, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (8, 8, 1)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (9, 9, 2)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (10, 10, 2)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (14, 3, 5)
INSERT [dbo].[tblMonKhoaHoc] ([id], [idMonChuyenNganh], [idNienKhoa]) VALUES (15, 4, 5)
SET IDENTITY_INSERT [dbo].[tblMonKhoaHoc] OFF
/****** Object:  Table [dbo].[tblLopMonHoc]    Script Date: 03/06/2019 10:59:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblLopMonHoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idMonKhoaHoc] [int] NOT NULL,
	[soLuong] [int] NOT NULL,
	[phongHoc] [nvarchar](20) NULL,
	[tenLop] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblLopMonHoc] ON
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (3, 2, 60, N'101', N'D14-011')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (4, 2, 60, N'102', N'D14-012')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (5, 3, 50, N'201', N'D14-013')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (6, 3, 50, N'201', N'D14-015')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (7, 9, 60, N'301', N'D15-011')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (8, 9, 60, N'301', N'D15-012')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (9, 10, 55, N'401', N'D15-013')
INSERT [dbo].[tblLopMonHoc] ([id], [idMonKhoaHoc], [soLuong], [phongHoc], [tenLop]) VALUES (10, 10, 55, N'402', N'D15-014')
SET IDENTITY_INSERT [dbo].[tblLopMonHoc] OFF
/****** Object:  Table [dbo].[tblSVLopMonHoc]    Script Date: 03/06/2019 10:59:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSVLopMonHoc](
	[idSv] [int] NOT NULL,
	[idLop] [int] NOT NULL,
	[diemChuyenCan] [float] NULL,
	[diemGiuaKi] [float] NULL,
	[diemThi] [float] NULL,
	[ghiChu] [nvarchar](max) NULL,
 CONSTRAINT [lopMonHoc_PK] PRIMARY KEY CLUSTERED 
(
	[idSv] ASC,
	[idLop] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (4, 3, 6, 6, 0, N'nghi 1 buoi')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (4, 7, 0, 0, 0, N'them vao sau')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (5, 3, 5, 0, 0, N' ')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (6, 3, 6, 4, 0, N'di muon')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (6, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (8, 3, 0, 7, 0, N'di muon')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (8, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (9, 3, 8, 7, 0, N'di muon')
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (9, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (10, 5, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (11, 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (11, 7, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (12, 7, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (13, 8, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (14, 8, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (15, 9, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (16, 10, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblSVLopMonHoc] ([idSv], [idLop], [diemChuyenCan], [diemGiuaKi], [diemThi], [ghiChu]) VALUES (17, 10, NULL, NULL, NULL, NULL)
/****** Object:  ForeignKey [FK__tblNienKh__idChu__1DE57479]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblNienKhoa]  WITH CHECK ADD FOREIGN KEY([idChuyenNganh])
REFERENCES [dbo].[tblChuyenNganh] ([id])
GO
/****** Object:  ForeignKey [FK__tblMonNga__idChu__1ED998B2]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblMonNganh]  WITH CHECK ADD FOREIGN KEY([idChuyenNganh])
REFERENCES [dbo].[tblChuyenNganh] ([id])
GO
/****** Object:  ForeignKey [FK__tblMonNga__idMon__1FCDBCEB]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblMonNganh]  WITH CHECK ADD FOREIGN KEY([idMon])
REFERENCES [dbo].[tblMonHoc] ([id])
GO
/****** Object:  ForeignKey [FK__tblSinhVi__idKho__20C1E124]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblSinhVien]  WITH CHECK ADD FOREIGN KEY([idKhoaHoc])
REFERENCES [dbo].[tblNienKhoa] ([id])
GO
/****** Object:  ForeignKey [FK__tblMonKho__idMon__21B6055D]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblMonKhoaHoc]  WITH CHECK ADD FOREIGN KEY([idMonChuyenNganh])
REFERENCES [dbo].[tblMonNganh] ([id])
GO
/****** Object:  ForeignKey [FK__tblMonKho__idNie__22AA2996]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblMonKhoaHoc]  WITH CHECK ADD FOREIGN KEY([idNienKhoa])
REFERENCES [dbo].[tblNienKhoa] ([id])
GO
/****** Object:  ForeignKey [FK__tblLopMon__idMon__239E4DCF]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblLopMonHoc]  WITH CHECK ADD FOREIGN KEY([idMonKhoaHoc])
REFERENCES [dbo].[tblMonKhoaHoc] ([id])
GO
/****** Object:  ForeignKey [FK__tblSVLopM__idLop__24927208]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblSVLopMonHoc]  WITH CHECK ADD FOREIGN KEY([idLop])
REFERENCES [dbo].[tblLopMonHoc] ([id])
GO
/****** Object:  ForeignKey [FK__tblSVLopMo__idSv__25869641]    Script Date: 03/06/2019 10:59:28 ******/
ALTER TABLE [dbo].[tblSVLopMonHoc]  WITH CHECK ADD FOREIGN KEY([idSv])
REFERENCES [dbo].[tblSinhVien] ([id])
GO
