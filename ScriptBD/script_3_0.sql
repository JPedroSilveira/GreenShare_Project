-- Database: GreenShare

-- DROP DATABASE "GreenShare";

CREATE DATABASE "GreenShare"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
CREATE SEQUENCE color_seq START 1;	
CREATE SEQUENCE climate_seq START 1;	
CREATE SEQUENCE growth_seq START 1;	
CREATE SEQUENCE address_seq START 1;	
CREATE SEQUENCE species_seq START 1;	
CREATE SEQUENCE flower_seq START 1;	
CREATE SEQUENCE flower_shop_seq START 1;	
CREATE SEQUENCE fruit_seq START 1;	
CREATE SEQUENCE offer_seq START 1;	
CREATE SEQUENCE permission_seq START 1;	
CREATE SEQUENCE achievement_seq START 1;	
CREATE SEQUENCE request_seq START 1;	
CREATE SEQUENCE soil_seq START 1;	
CREATE SEQUENCE suggestion_seq START 1;	
CREATE SEQUENCE user_seq START 1;	
CREATE SEQUENCE user_soil_seq START 1;
CREATE SEQUENCE month_seq START 1;
CREATE SEQUENCE post_seq START 1;