import hudson.model.*
import hudson.triggers.*

/**
* The script updates TimeTrigger option to run a specific list of jobs in the midnight.
* If a job does not use "Build periodically" option ("disabled job"), the job will be skipped.
* 
* Use http://jenkins.sme.com:9080/script to run the script.
**/


TriggerDescriptor TIMER_TRIGGER_DESCRIPTOR = Hudson.instance.getDescriptorOrDie(TimerTrigger.class);
def JOB_TO_TIMER_TRIGGER = [
  
	// Monday
	"Backend.Job1.Nightly" :				"00 01 * * 1",
		"Backend.Job2.Nightly" :			"30 01 * * 1",
			"Backend.Job3.Nightly" :		"00 03 * * 1",
				"Backend.Job4.Nightly" :	"30 03 * * 1",

	// Tuesday
	"Backend.Job5.Nightly" :				"00 01 * * 2",
		"Backend.Job6.Nightly" :			"30 01 * * 2",
			"Backend.Job7.Nightly" :		"00 03 * * 2",
				"Backend.Job8.Nightly" :	"30 03 * * 2",
  
	// Wednesday
	"Backend.Job9.Nightly" :				"00 01 * * 3",
		"Backend.Job10.Nightly" :			"30 01 * * 3",
			"Backend.Job11.Nightly" :		"00 03 * * 3",
				"Backend.Job12.Nightly" :	"30 03 * * 3",
  	
  
	// Thursday
	"Backend.Job13.Nightly" : 				"00 01 * * 4",
		"Backend.Job14.Nightly" : 			"30 01 * * 4",
			"Backend.Job15.Nightly" : 		"00 03 * * 4",
				"Backend.Hob16.Nightly" : 	"30 03 * * 4",

  
	// Friday
	"Backend.Hob17.Nightly" : 				"00 01 * * 5",
		"Backend.Job18.Nightly" : 			"30 01 * * 5",
			"Backend.Job19.Nightly" : 		"00 03 * * 5",
				"Backend.Job20.Nightly" : 	"30 03 * * 1"
];


for(item in Jenkins.instance.getAllItems(Job)) 
{
	def timerTrigger = item.getTriggers().get(TIMER_TRIGGER_DESCRIPTOR);
	if (timerTrigger) 
	{

		def timerSpec = JOB_TO_TIMER_TRIGGER.get(item.name);
		if (timerSpec)
		{
			println("Current timer options: " + timerTrigger.spec + " in job: " + item.name);
			println("Set new trigger options: " + timerSpec + " in job: " + item.name);
			println("\n");
	      
			item.addTrigger(new TimerTrigger(timerSpec));
			item.save();
		}
	}
}

return