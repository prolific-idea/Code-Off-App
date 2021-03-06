USE [CodeOff]
GO
/****** Object:  Table [dbo].[AppUser]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AppUser](
	[appUserId] [int] NOT NULL,
	[appUserDetailsId] [int] NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varbinary](100) NULL,
	[enabled] [bit] NULL,
 CONSTRAINT [PK_AppUser] PRIMARY KEY CLUSTERED 
(
	[appUserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AppUserDetails]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AppUserDetails](
	[appUserDetailsId] [int] NOT NULL,
	[firstName] [varchar](50) NULL,
	[lastName] [varchar](50) NULL,
	[emailAddress] [varchar](100) NULL,
 CONSTRAINT [PK_AppUserDetails] PRIMARY KEY CLUSTERED 
(
	[appUserDetailsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AppUserRole]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppUserRole](
	[appUserRoleId] [int] NOT NULL,
	[appUserId] [int] NOT NULL,
	[roleId] [int] NOT NULL,
 CONSTRAINT [PK_AppUserRole] PRIMARY KEY CLUSTERED 
(
	[appUserRoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Challenge]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Challenge](
	[challengeId] [int] NOT NULL,
	[solution] [varbinary](max) NOT NULL,
	[url] [varchar](100) NOT NULL,
	[solutionFilePath] [varchar](100) NULL,
	[startDate] [date] NULL,
	[endDate] [date] NOT NULL,
 CONSTRAINT [PK_Challenge] PRIMARY KEY CLUSTERED 
(
	[challengeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Entry]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Entry](
	[entryId] [int] NOT NULL,
	[solution] [varbinary](max) NULL,
	[date] [date] NOT NULL,
	[personId] [int] NOT NULL,
	[techId] [int] NOT NULL,
	[url] [varchar](100) NULL,
	[challengeId] [int] NOT NULL,
	[result] [int] NULL,
 CONSTRAINT [PK_Entry] PRIMARY KEY CLUSTERED 
(
	[entryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Person]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Person](
	[personId] [int] NOT NULL,
	[username] [varchar](50) NOT NULL,
	[firstName] [varchar](50) NULL,
	[lastName] [varchar](50) NULL,
	[score] [int] NOT NULL,
	[url] [varchar](50) NULL,
 CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED 
(
	[personId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[roleId] [int] NOT NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Technology]    Script Date: 2016/02/26 11:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Technology](
	[techId] [int] NOT NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [PK_Technology] PRIMARY KEY CLUSTERED 
(
	[techId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[AppUser]  WITH CHECK ADD  CONSTRAINT [FK_AppUser_AppUserDetails] FOREIGN KEY([appUserDetailsId])
REFERENCES [dbo].[AppUserDetails] ([appUserDetailsId])
GO
ALTER TABLE [dbo].[AppUser] CHECK CONSTRAINT [FK_AppUser_AppUserDetails]
GO
ALTER TABLE [dbo].[AppUserRole]  WITH CHECK ADD  CONSTRAINT [FK_AppUserRole_AppUser] FOREIGN KEY([appUserId])
REFERENCES [dbo].[AppUser] ([appUserId])
GO
ALTER TABLE [dbo].[AppUserRole] CHECK CONSTRAINT [FK_AppUserRole_AppUser]
GO
ALTER TABLE [dbo].[AppUserRole]  WITH CHECK ADD  CONSTRAINT [FK_AppUserRole_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([roleId])
GO
ALTER TABLE [dbo].[AppUserRole] CHECK CONSTRAINT [FK_AppUserRole_Role]
GO
ALTER TABLE [dbo].[Entry]  WITH CHECK ADD  CONSTRAINT [FK_Entry_Challenge] FOREIGN KEY([challengeId])
REFERENCES [dbo].[Challenge] ([challengeId])
GO
ALTER TABLE [dbo].[Entry] CHECK CONSTRAINT [FK_Entry_Challenge]
GO
ALTER TABLE [dbo].[Entry]  WITH CHECK ADD  CONSTRAINT [FK_Entry_Person] FOREIGN KEY([personId])
REFERENCES [dbo].[Person] ([personId])
GO
ALTER TABLE [dbo].[Entry] CHECK CONSTRAINT [FK_Entry_Person]
GO
ALTER TABLE [dbo].[Entry]  WITH CHECK ADD  CONSTRAINT [FK_Entry_Technology] FOREIGN KEY([techId])
REFERENCES [dbo].[Technology] ([techId])
GO
ALTER TABLE [dbo].[Entry] CHECK CONSTRAINT [FK_Entry_Technology]
GO
