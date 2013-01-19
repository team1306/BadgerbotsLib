BadgerbotsLib
===

#Important Note for 2013 Season
FIRST changed the way the FRC Java extensions work for the 2013 season. Team 1306 is currently working on integrating the new structure into our code.

##License
Copyright 2012 by Contributors

Licensed under the [GPLv3](http://www.gnu.org/licenses/gpl.txt).

##Prerequisites
BadgerbotsLib is based on the [WPILibJ](http://firstforge.wpi.edu/sf/projects/wpilib) library. 
You'll need it installed in NetBeans, so follow the instructions on their site.

Once in NetBeans, you'll want to add `/sunspotfrcsdk/lib/squawk_device.jar` and `/sunspotfrcsdk/lib/WPILibJ/classes.jar` to the classpath if they are not automatically added.

##Usage
To use BadgerbotsLib in an FRC NetBeans project, you'll have to insert some code (found below) into the build.xml.
NetBeans doesn't support adding libraries to anything but its own projects, so the contents of BadgerbotsLib are dynamically patched by the following code:

	<!-- 1306 stuff follows -->

    <target name="-pre-compile">
        <exec executable="rm">
            <arg value="-rf" />
            <arg value="./src/org/badgerbots/lib" />
        </exec>
        <exec executable="cp">
            <arg value="-R" />
            <arg value="../BadgerbotsLib/src/org" />
            <arg value="src/" />
        </exec>
    </target>
    <target name="-post-compile">
        <exec executable="rm">
            <arg value="-rf" />
            <arg value="./src/org" />
        </exec>
    </target>

This should be placed after the line `<import file="${sunspot.home}/build.xml"/>`.
