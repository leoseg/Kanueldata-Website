# Kanueldata-Website
Spring-Boot Webcontainer for displaying feauture data extracted from the Bioimpedance-, Breathing- and EMG-Signals measured at the neck while swallowing with electrodes.

Each plot is a scatter plot representing one feature at the x-axis and one at the y-axis.
Data is displayed summarized (so one datapoint for one feature) or with all features (so one datapoint is for one swallow).
Normal categorization of patients is with the labels "no kanuele", "bisalski" and "geblockt". Where "biesalski" means the kanuele is half opened and "geblockt" means its closed. 
Other categorization option is binaer where the labels are either "kanuel" or "no kanuele".


## Purpose
Mainly for practicing Spring Boot and Java. Originated from one of my Projekts at the TU-Berlin. 
Project can be run in a dockerfile local. Postgress authentication is needed.


