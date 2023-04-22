use toeiconline;

UPDATE listenguideline SET image = 'slide1.png' WHERE listenguidelineid >=9 AND listenguidelineid <=16;

ALTER TABLE listenguideline MODIFY COLUMN title VARCHAR(512) NOT NULL;
ALTER TABLE listenguideline MODIFY COLUMN image VARCHAR(255) NOT NULL;
ALTER TABLE listenguideline MODIFY COLUMN content TEXT NOT NULL;