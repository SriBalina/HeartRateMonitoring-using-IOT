#define USE_ARDUINO_INTERRUPTS true    // Set-up low-level interrupts for most acurate BPM math.
#include <PulseSensorPlayground.h> // Includes the PulseSensorPlayground Library.   
#define sensorPin A1

//  Variables
const int PulseWire = 0;       // PulseSensor PURPLE WIRE connected to ANALOG PIN 0       
int Threshold = 550;           // Use the "Gettting Started Project" to fine-tune Threshold Value beyond default setting.        
                               // Otherwise leave the default "550" value.    
PulseSensorPlayground pulseSensor;  // Creates an instance of the PulseSensorPlayground object called "pulseSensor"


void setup() {   

  Serial.begin(9600);          // For Serial Monitor
   pinMode(11,OUTPUT);
  // Configure the PulseSensor object, by assigning our variables to it. 
  pulseSensor.analogInput(PulseWire);        //auto-magically blink Arduino's LED with heartbeat.
  pulseSensor.setThreshold(Threshold);   

  // Double-check the "pulseSensor" object was created and "began" seeing a signal. 
   if (pulseSensor.begin()) {
    Serial.println("We created a pulseSensor Object !");  //This prints one time at Arduino power-up,  or on Arduino reset.  
  }
}



void loop() {
  // Get the voltage reading from the LM35
  int reading = analogRead(sensorPin);

  // Convert that reading into voltage
  float voltage = reading * (5.0 / 1024.0);

  // Convert the voltage into the temperature in Celsius
  float temperatureC = voltage * 100;

 int myBPM = pulseSensor.getBeatsPerMinute(); 
  // Calls function on our pulseSensor object that returns BPM as an "int".
 


if (pulseSensor.sawStartOfBeat()) {  
  Serial.print("\n");
  Serial.print("\n");     // Constantly test to see if "a beat happened".  // If test is "true", print a message "a heartbeat happened".
 Serial.print("BPM: ");                        // Print phrase "BPM: " 
 Serial.print(myBPM);  // Print the value inside of myBPM. 
  Serial.print("  Temperature: ");
  Serial.print(temperatureC); // shows degree symbol
  Serial.print("C  |  ");
Serial.print("\n");

  if(temperatureC>40 and myBPM>90){
    tone(11,200);
    delay(1000);
    noTone(11);
    delay(1500);
  }
}

  delay(1000);                    // considered best practice in a simple sketch.

}
