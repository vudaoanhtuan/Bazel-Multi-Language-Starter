from proto import thing_pb2


if __name__ == '__main__':
    thing = thing_pb2.Thing(name="Hello world")
    print(thing)
