#!/bin/bash
oc set route-backends hotel-booking-web-app hotel-booking-web-app=0 hotel-booking-web-app-v2=100 -n hotel-booking-web-app
