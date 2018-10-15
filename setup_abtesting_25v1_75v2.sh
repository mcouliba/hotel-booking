#!/bin/bash
oc set route-backends hotel-booking-web-app hotel-booking-web-app=25 hotel-booking-web-app-v2=75 -n hotel-booking-web-app
