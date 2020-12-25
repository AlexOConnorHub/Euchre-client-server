# module imports
import bottle, json

# my module imports
import game, logic

# global variabls
serverIP = "localhost"
serverPort = 8080
public = 'public/'
private = 'private/'
numberOfRooms = 4
games = []

# Make rooms
for _ in range(numberOfRooms):
    games.append(game.game())

# Make a play
@bottle.route('/euchre/play', method="POST")
def play():
    pass

# Update others
@bottle.route('/euchre/update')
def sendToPeople():
    bottle.response.content_type = 'text/event-stream'
    yield whatsHappening()

# Sit at table
@bottle.route('/euchre/<table:int>')
def sitDown(table):
    if games[table].howManySitting() != 4:
        return { 'state' : True }
    else:
        return { 'state' : False }

# Get table count
@bottle.route('/euchre/tables')
def getTables():
    final = []
    for game in games:
        final.append(str(game.howManySitting()))
    return json.dumps(final)

application = bottle.default_app()

def whatsHappening():
    return f'retry: 100\n\n data: test\n\n'

if __name__ == "__main__":

    # Make everything in /public public
    @bottle.route('/<filepath:path>')
    def server_static(filepath):
        return bottle.static_file(filepath, root=public)
        
    bottle.run(host=serverIP, port=serverPort, debug=True)