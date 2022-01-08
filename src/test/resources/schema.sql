CREATE TABLE testtable (
    "feature1" double precision,
    "feature2" double precision,
    "feature3" double precision
                                     );

CREATE TABLE featuredata (
                           "feature1" double precision,
                           "PatNr" double precision,
                           "some other feature" double precision
);

INSERT INTO featuredata ("PatNr","feature1") VALUES
                                                       (1,2),
                                                       (1,3),
                                                       (1,7),
                                                       (2,7),
                                                       (2,9),
                                                       (2,29),
                                                       (3,6),
                                                       (3,4),
                                                       (3,5),
                                                       (4,7),
                                                       (4,9);



CREATE TABLE patientdata (
    "PatNr" bigint,
    "Status nach" char(50)
);

INSERT INTO patientdata ("PatNr", "Status nach") VALUES
(1,'keine kanuele'),
(2,'geblockt'),
(3,'keine kanuele'),
(4,'Biesalski');




