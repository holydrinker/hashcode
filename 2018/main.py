import hashcode

vehicles, rides, bonus, steps = hashcode.get_data('e_high_bonus.in')
output_dict = {}
for v in vehicles:
    output_dict[v[0]] = []

for ride in rides:
    vehicle_ride_score = hashcode.assign_ride_to_vehicle(vehicles, ride, steps)
    vehicle_id = vehicle_ride_score[0]
    ride_id = vehicle_ride_score[1]
    score = vehicle_ride_score[2]

    vehicle_to_change = hashcode.recover_from_collection(vehicles, vehicle_ride_score[0])
    ride_to_change = hashcode.recover_from_collection(rides, vehicle_ride_score[1])

    hashcode.update_vehicle(vehicles, vehicle_to_change, ride_to_change, score)

    hashcode.update_dictionary(output_dict, vehicle_ride_score)

hashcode.write_output(output_dict, 'e')
