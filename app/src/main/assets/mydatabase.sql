BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `android_metadata` (
	`locale`	TEXT DEFAULT 'en_US'
);
INSERT INTO `android_metadata` VALUES ('en_US');
CREATE TABLE IF NOT EXISTS `QuestionDatabase` (
	`id`	TEXT NOT NULL,
	`question`	TEXT NOT NULL,
	`answer1`	TEXT NOT NULL,
	`answer2`	TEXT NOT NULL,
	`answer3`	TEXT NOT NULL,
	`answer4`	TEXT NOT NULL,
	`correctanswer`	TEXT NOT NULL,
	`genre`	TEXT NOT NULL,
	PRIMARY KEY(`id`)
);
INSERT INTO `QuestionDatabase` VALUES ('id','Question','answer1','answer2','answer3','answer4','correctanswer','genre');
INSERT INTO `QuestionDatabase` VALUES ('1','Fråga','Svar a','Svar b','Svar c','Svar d','Rätt svar','Kategori');
INSERT INTO `QuestionDatabase` VALUES ('2','Va heter världens svagaste man','kanpe','knape','knaoe','knapelicous','knape','');
INSERT INTO `QuestionDatabase` VALUES ('3','2+2-1 ?','Unnecessarily long problem','Quick maths','3','To easy','Quick maths','Humor');
INSERT INTO `QuestionDatabase` VALUES ('4','','','','','','','');
INSERT INTO `QuestionDatabase` VALUES ('5','How does da ting go?','skrrrrrrraaaah','quack','skidi-bi-bap-bap','bom','skrrrrrrraaaah','Humor');
INSERT INTO `QuestionDatabase` VALUES ('6','Who is Iron Mans alter ego?','Eddard Stark','Tobey Stark','Tony Stark','Robert Stark','Tony Stark','Film');
INSERT INTO `QuestionDatabase` VALUES ('7','Which country is going to host FIFA World Cup 2022?','Kina','Qatar','Ryssland','Brasilien','Qatar','Sport');
INSERT INTO `QuestionDatabase` VALUES ('8','How many periods are there in a basketball game?','4','2','3','6','4','Sport');
INSERT INTO `QuestionDatabase` VALUES ('9','Which golfclub is allowed on green?','Driver','5 iron','Putter','Lob wedge','Putter','Sport');
INSERT INTO `QuestionDatabase` VALUES ('10','Which team won the The Internationall 3?','Alliance','NA''VI','Team Liquid','Fnatic','Alliance','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('11','What does NIP stand for?','Ninjas in Pyjamas','Nougat is Power','Nervous iguanas Partying','Never in Pain','Ninjas in pyjamas','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('12','What would make the economy collapse?','Boycotters','No one can','Protestants','Some green paper bois','Some green paper bois','Humor');
INSERT INTO `QuestionDatabase` VALUES ('13','What gender will the new Doctor Who have?','Man','Woman','Apache helicopter','I''m offended','Woman','Humor');
INSERT INTO `QuestionDatabase` VALUES ('14','What movie aired first in cinema?','The Matrix','Hairy pothead and the philosopher''s stone','Lord of the rings','The fast and the furious','The Matrix','Film');
INSERT INTO `QuestionDatabase` VALUES ('15','What was Benjamin Buttons curious case?','He aged backwards','He couldn''t feel any pain','He had a remarkable memory','He didn''t need to sleep','He aged backwards','Film');
INSERT INTO `QuestionDatabase` VALUES ('16','Which team hasn´t Zlatan Ibrahimovic played for?','Manchester United','Malmö FF','Ajax','PSV Eindhoven','PSV Eindhoven','Sport');
INSERT INTO `QuestionDatabase` VALUES ('17','Who is a famous basketball player?','Kevin Hart','James Brown','LeBron James','Kevin Dillon','LeBron James','Sport');
INSERT INTO `QuestionDatabase` VALUES ('18','Who is the person Deadpool is looking for?','Adam','Francis','George','Hewey','Francis','Film');
INSERT INTO `QuestionDatabase` VALUES ('19','From which country is the sprinter Usain Bolt from?','Bahamas','Jamaica','Haiti','USA','Jamaica','Sport');
INSERT INTO `QuestionDatabase` VALUES ('20','Which movie was the most succesfull manga adaptation?','Dragonball','Avatar the last airbender','Death Note (Netflix)','Gantz','Gantz','Film');
INSERT INTO `QuestionDatabase` VALUES ('21','Which sport doesn´t have a round ball?','Tennis','Rugby','Baseball','Basketball','Rugby','Sport');
INSERT INTO `QuestionDatabase` VALUES ('22','In which sport can you make a slam dunk?','Table Tennis','Bowling','Dart','Basketball','Basketball','Sport');
INSERT INTO `QuestionDatabase` VALUES ('23','A perfect game in bowling consist of how many strikes?','10','13','1','12','12','Sport');
INSERT INTO `QuestionDatabase` VALUES ('24','Finish the titel: Indiana jones and the last...','Parade','Ark','Lemonade','Crusade','Crusade','Film');
INSERT INTO `QuestionDatabase` VALUES ('25','Harrison Ford played president in...','Air Force Wan','Air Force One','Air Farce One','Air Force Null','Air Force One','Film');
INSERT INTO `QuestionDatabase` VALUES ('26','Vilken är den tyngsta delen i en atom?','elektronerna','Kärnan','kvarkarna','Neutronerna','Kärnan','Vetenskap');
INSERT INTO `QuestionDatabase` VALUES ('27','Which year was the single "paparazzi" by Lady Gaga released?','2002','2015','2009','2012','2009','Musik');
INSERT INTO `QuestionDatabase` VALUES ('28','In which swedish city did the Eurovision song contest 2013 take place?','Stockholm','Borås','Göteborg','Malmö','Malmö','Musik');
INSERT INTO `QuestionDatabase` VALUES ('29','Which of the following isn´t a Rihanna song?','SOS','Hello','Umbrella','Diamonds','Hello','Musik');
INSERT INTO `QuestionDatabase` VALUES ('30','Which year was Madonna born?','1945','1958','1960','1985','1958','Musik');
INSERT INTO `QuestionDatabase` VALUES ('31','Which was Kanye West debut single?','Gold digger','Jesus walks','Slow jamz','Through the wire','Through the wire','Musik');
INSERT INTO `QuestionDatabase` VALUES ('32','How many players are there normaly in a league of legends game?','10','2','4','6','10','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('33','How many cards does a Hearthstone deck normaly contain?','20','50','30','25','30','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('34','How high was the total prize pool at The international 7?','24 787 916 $','1 020 542 $','3 252 898 $','59 478 321 $','24 787 916 $','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('35','Wich of the following isn´t a dota 2 hero?','Earthshaker','Bastion','Razor','Windrunner','Bastion','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('36','How many lanes are there in a dota 2 game?','1','2','3','4','3','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('37','In wich city did the first International take place?','Cologne','Seattle','Soeul','Los Angeles','Cologne','E-Sport');
INSERT INTO `QuestionDatabase` VALUES ('38','Who was a member of N´sync?','Nick Carter','Brian Littrell','Lance Bass','Brian McFadden','Lance Bass','Musik');
INSERT INTO `QuestionDatabase` VALUES ('39','Which member left the Spice Girls in 1998?','Geri Halliwell','Melanie Brown','Victoria Beckham','Emma Bunton','Geri Halliwell','Musik');
INSERT INTO `QuestionDatabase` VALUES ('40','Which is the all time best-selling album?','Justin Bieber - My World 2.0','Michael Jackson - Thriller','The Beatles - Sgt. Pepper''s Lonely Hearts Club Band','Pink Floyd - The Dark Side of the Moon','Michael Jackson - Thriller','Musik');
INSERT INTO `QuestionDatabase` VALUES ('41','Which artist released the single "Boombastic"?','Shaggy','Akon','Sean Paul','Eminem','Shaggy','Musik');
INSERT INTO `QuestionDatabase` VALUES ('42','Which of the following isn´t a musical instrument?','Stylophone','Okapi','Didgeridoo','Cimbalom','Okapi','Musik');
INSERT INTO `QuestionDatabase` VALUES ('43','(Bild på en mustang) what car is this?','BMW M5','Ford Mustang','Dodge Viper','Volvo s60','Ford Mustang','Random');
INSERT INTO `QuestionDatabase` VALUES ('44','What does GGWP stand for?','Good game well played','girs gone wild policy','go go web power','girl go wild policy','Good game well played','E-sport');
INSERT INTO `QuestionDatabase` VALUES ('45','So.. whatsup?','dunno','o.O','bruv pls.','just chillin','bruv pls.','Humor');
COMMIT;
