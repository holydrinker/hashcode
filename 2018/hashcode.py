def get_data(dataset):
    with open(dataset)as file:
        line = file.readline().split(" ")
        vehicles = []
        rides = []
        r = int(line[0])
        c = int(line[1])
        for i in range(int(line[2])):
            vehicles.append([i, 0, 0, 0])
        bonus = int(line[4])
        steps = int(line[5])
        for j in range(int(line[3])):
            line = file.readline().split(" ")
            rides.append([j, int(line[0]), int(line[1]), int(line[2]), int(line[3]), int(line[4]), int(line[5])])
    return vehicles, rides, bonus, steps


def ride_score(car, ride):
    def man_distance(x1, y1, x2, y2):
        return abs(x1 - x2) + abs(y1 - y2)

    score = 0

    dist_to_arrive = man_distance(car[1], car[2], ride[1], ride[2])  # pos corrente -> pos partenza viaggio
    score += dist_to_arrive
    if (car[3] + dist_to_arrive < ride[5]):
        score += ride[5] - (car[3] + dist_to_arrive)

    score += man_distance(ride[1], ride[2], ride[3], ride[4])

    return score


def assign_ride_to_vehicle(vehicles, ride, max_steps):
    VEHICLE = 0
    SCORE = 1
    ID = 0
    STEPS = 3

    scored_candidates = [(candidate, ride_score(ride=ride, car=candidate)) for candidate in vehicles]
    winner_pair_list = sorted(scored_candidates, key=lambda t: t[1])
    winner_pair = winner_pair_list[0]
    winner_vehicle = winner_pair[VEHICLE]
    best_score = winner_pair[SCORE]

    while winner_vehicle[STEPS] + best_score > max_steps and len(winner_pair_list) > 1:
        vehicles = [v for v in vehicles if v[ID] != winner_vehicle[ID]]
        winner_pair_list = winner_pair_list[1:]
        winner_pair = winner_pair_list[0]
        winner_vehicle = winner_pair[VEHICLE]
        best_score = winner_pair[SCORE]

    return [winner_vehicle[ID], ride[ID], best_score]


def update_vehicle(vehicles, vehicle, ride, score):
    ID = 0
    RIDE_DEST_X = 3
    RIDE_DEST_Y = 4
    VEHICLE_NEXT_AVAILABLE_X = 1
    VEHICLE_NEXT_AVAILABLE_Y = 2
    STEPS_DONE = 3

    vehicle[VEHICLE_NEXT_AVAILABLE_X] = ride[RIDE_DEST_X]
    vehicle[VEHICLE_NEXT_AVAILABLE_Y] = ride[RIDE_DEST_Y]
    vehicle[STEPS_DONE] = vehicle[STEPS_DONE] + score

    return [v for v in vehicles if v[ID] != vehicle[ID]].append(vehicle)


def recover_from_collection(collection, id):
    return [v for v in collection if v[0] == id][0]


def update_dictionary(dic, coppia):
    dic[coppia[0]].append(coppia[1])


def write_output(dic, filename):
    output = open('output/{}.txt'.format(filename), "w")
    for keys, items in dic.items():
        string = str(len(items)) + " "
        for item in items:
            string = string + str(item) + " "
        string = string + '\n'
        output.write(string)
    output.close()
