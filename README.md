## Guitar Simulator

Overview:

A guitar music player that can play MIDI files.

## How it was built
At the project's core, it utilizes the Karplus–Strong string synthesis algorithm to replicate the sound of a plucked guitar string. The algorithm uses a `Deque` (double ended queue) data structure, where the contents of the MIDI file are placed into the `Deque`. Then the algorithm continuously dequeues, calculates the dequeued values (which is the frequency of the guitar string), and plays it using `StdAudio.play` from Princeton's standard library. This function works by telling the diaphragm of your speaker how much it should extend, and the air displaced is the sound that is coming out of your speaker. The program continously repeats this algorithm for the contents of the file, which then the guitar string's patterns can be recogized as a ... song!

## Project Demo

Video : https://www.youtube.com/watch?v=7p4L-bUN9NI


## Installation and Setup Instructions

1. Clone down this repository
2. Import code library into your favorite IDE (must support Java)
3. Pick a song/beat of your choice. It must be in a MIDI file. I recommend BitMidi, which is a popular library that has many downloadable MIDI files of popular songs.
4. Place the downloaded file into a folder of your choice
5. Search for the path to the file using terminal , `cd` to the file and `pwd` to show the path of the file. Copy this.
6. In the TTFAF file, paste the path of the file in the prompted area and press `run`.
7. Vibe.
