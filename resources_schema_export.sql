alter table Card drop constraint FK1FEF3056BBD80D
alter table Card drop constraint FK1FEF30557BA941
alter table Card drop constraint FK1FEF30A8A95D7B
alter table Comment drop constraint FK9BDE863FEC5A029B
alter table Fixture_Match drop constraint FK67150BCF169C29CD
alter table Fixture_Match drop constraint FK67150BCFE3CEB2FF
alter table Goal drop constraint FK21F33356BBD80D
alter table Goal drop constraint FK21F333557BA941
alter table Goal drop constraint FK21F333A8A95D7B
alter table Match drop constraint FK46AE9A58EEDE9BC
alter table Match drop constraint FK46AE9A5103C584F
alter table Match drop constraint FK46AE9A5E3CEB2FF
alter table Match_Card drop constraint FKE5E8FD8AF77A0C29
alter table Match_Card drop constraint FKE5E8FD8AA8A95D7B
alter table Match_Goal drop constraint FKE5EB018D130F2B18
alter table Match_Goal drop constraint FKE5EB018DA8A95D7B
alter table Player drop constraint FK8EA38701557BA941
alter table Player_Card drop constraint FK848859AE33132850
alter table Player_Card drop constraint FK848859AE56BBD80D
alter table Player_Card drop constraint FK848859AE8A99345F
alter table Player_Card drop constraint FK848859AEE25238ED
alter table Player_Goal drop constraint FK848A5DB156BBD80D
alter table Player_Goal drop constraint FK848A5DB1437D2AE5
alter table Team drop constraint FK27B67D4EC809A5
alter table Team_Player drop constraint FKD4E5F703C1D06D9E
alter table Team_Player drop constraint FKD4E5F703557BA941
alter table Tournament drop constraint FK3B743609E3CEB2FF
alter table Tournament_Team drop constraint FK77B96F38EEB079A
alter table Tournament_Team drop constraint FK77B96F34EC809A5
alter table User drop constraint FK285FEB4EC809A5
drop table Card if exists
drop table Comment if exists
drop table Fixture if exists
drop table Fixture_Match if exists
drop table Goal if exists
drop table Match if exists
drop table Match_Card if exists
drop table Match_Goal if exists
drop table Player if exists
drop table Player_Card if exists
drop table Player_Goal if exists
drop table Team if exists
drop table Team_Player if exists
drop table Tournament if exists
drop table Tournament_Team if exists
drop table User if exists
create table Card (idCard integer generated by default as identity (start with 1), CardColour integer, match_Id_Match integer, player_Id_Player integer, team_Id_Team integer, primary key (idCard))
create table Comment (Id_Player integer generated by default as identity (start with 1), Comment varchar(255), Date varchar(255), user_User_Name varchar(20), primary key (Id_Player))
create table Fixture (Id_Fixture integer generated by default as identity (start with 1), primary key (Id_Fixture))
create table Fixture_Match (Fixture_Id_Fixture integer not null, matches_Id_Match integer not null, unique (matches_Id_Match))
create table Goal (IdGoal integer generated by default as identity (start with 1), match_Id_Match integer, player_Id_Player integer, team_Id_Team integer, primary key (IdGoal))
create table Match (Id_Match integer generated by default as identity (start with 1), date date, Fecha_Nro integer, GuestResult integer, LocalResult integer, fixture_Id_Fixture integer, guest_Id_Team integer, local_Id_Team integer, primary key (Id_Match))
create table Match_Card (Match_Id_Match integer not null, playerGCard_idCard integer not null, unique (playerGCard_idCard))
create table Match_Goal (Match_Id_Match integer not null, highScoring_IdGoal integer not null, unique (highScoring_IdGoal))
create table Player (Id_Player integer generated by default as identity (start with 1), Player_Email varchar(255), Player_LastName varchar(255) not null, Player_Name varchar(255) not null, Player_Photo longvarbinary, Player_Position varchar(255), team_Id_Team integer, primary key (Id_Player))
create table Player_Card (Player_Id_Player integer not null, yellowCards_idCard integer not null, redCards_idCard integer not null, greenCards_idCard integer not null, unique (yellowCards_idCard), unique (greenCards_idCard), unique (redCards_idCard))
create table Player_Goal (Player_Id_Player integer not null, goals_IdGoal integer not null, unique (goals_IdGoal))
create table Team (Id_Team integer generated by default as identity (start with 1), Team_Description varchar(255), Team_Diference integer, Team_GC integer, Team_GF integer, Team_Name varchar(255) not null, Team_PE integer, Team_PG integer, TEAM_IMAGE longvarbinary, Team_PJ integer, Team_Position integer, Team_PP integer, Team_Points integer, tournament_Id_Tournament integer, primary key (Id_Team))
create table Team_Player (Team_Id_Team integer not null, players_Id_Player integer not null, unique (players_Id_Player))
create table Tournament (Id_Tournament integer generated by default as identity (start with 1), Descripcion varchar(255), Name varchar(255) not null, Tournament_Photo longvarbinary, fixture_Id_Fixture integer, primary key (Id_Tournament), unique (Name))
create table Tournament_Team (Tournament_Id_Tournament integer not null, teams_Id_Team integer not null, unique (teams_Id_Team))
create table User (userType varchar(31) not null, User_Name varchar(20) not null, Email varchar(255) not null, Last_Name varchar(255) not null, Name varchar(255) not null, Password varchar(10) not null, User_Photo longvarbinary, Privilege integer, tournament_Id_Tournament integer, primary key (User_Name), unique (User_Name))
alter table Card add constraint FK1FEF3056BBD80D foreign key (player_Id_Player) references Player
alter table Card add constraint FK1FEF30557BA941 foreign key (team_Id_Team) references Team
alter table Card add constraint FK1FEF30A8A95D7B foreign key (match_Id_Match) references Match
alter table Comment add constraint FK9BDE863FEC5A029B foreign key (user_User_Name) references User
alter table Fixture_Match add constraint FK67150BCF169C29CD foreign key (matches_Id_Match) references Match
alter table Fixture_Match add constraint FK67150BCFE3CEB2FF foreign key (Fixture_Id_Fixture) references Fixture
alter table Goal add constraint FK21F33356BBD80D foreign key (player_Id_Player) references Player
alter table Goal add constraint FK21F333557BA941 foreign key (team_Id_Team) references Team
alter table Goal add constraint FK21F333A8A95D7B foreign key (match_Id_Match) references Match
alter table Match add constraint FK46AE9A58EEDE9BC foreign key (guest_Id_Team) references Team
alter table Match add constraint FK46AE9A5103C584F foreign key (local_Id_Team) references Team
alter table Match add constraint FK46AE9A5E3CEB2FF foreign key (fixture_Id_Fixture) references Fixture
alter table Match_Card add constraint FKE5E8FD8AF77A0C29 foreign key (playerGCard_idCard) references Card
alter table Match_Card add constraint FKE5E8FD8AA8A95D7B foreign key (Match_Id_Match) references Match
alter table Match_Goal add constraint FKE5EB018D130F2B18 foreign key (highScoring_IdGoal) references Goal
alter table Match_Goal add constraint FKE5EB018DA8A95D7B foreign key (Match_Id_Match) references Match
alter table Player add constraint FK8EA38701557BA941 foreign key (team_Id_Team) references Team
alter table Player_Card add constraint FK848859AE33132850 foreign key (yellowCards_idCard) references Card
alter table Player_Card add constraint FK848859AE56BBD80D foreign key (Player_Id_Player) references Player
alter table Player_Card add constraint FK848859AE8A99345F foreign key (greenCards_idCard) references Card
alter table Player_Card add constraint FK848859AEE25238ED foreign key (redCards_idCard) references Card
alter table Player_Goal add constraint FK848A5DB156BBD80D foreign key (Player_Id_Player) references Player
alter table Player_Goal add constraint FK848A5DB1437D2AE5 foreign key (goals_IdGoal) references Goal
alter table Team add constraint FK27B67D4EC809A5 foreign key (tournament_Id_Tournament) references Tournament
alter table Team_Player add constraint FKD4E5F703C1D06D9E foreign key (players_Id_Player) references Player
alter table Team_Player add constraint FKD4E5F703557BA941 foreign key (Team_Id_Team) references Team
alter table Tournament add constraint FK3B743609E3CEB2FF foreign key (fixture_Id_Fixture) references Fixture
alter table Tournament_Team add constraint FK77B96F38EEB079A foreign key (teams_Id_Team) references Team
alter table Tournament_Team add constraint FK77B96F34EC809A5 foreign key (Tournament_Id_Tournament) references Tournament
alter table User add constraint FK285FEB4EC809A5 foreign key (tournament_Id_Tournament) references Tournament
