import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.exceptions.NotFoundException;
import com.messagebird.objects.VoiceMessageResponse;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.UnauthorizedException;

/**
 * Created by rvt on 1/8/15.
 */
public class ExampleViewVoiceMessage {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please specify your access key and a voice message ID : java -jar <this jar file> test_accesskey e8077d803532c0b5937c639b60216938");
            return;
        }

        // First create your service object
        final MessageBirdService wsr = new MessageBirdServiceImpl(args[0]);

        // Add the service to the client
        final MessageBirdClient messageBirdClient = new MessageBirdClient(wsr);

        try {
            // Get Hlr using msgId and msisdn
            System.out.println("Getting message info message:");
            final VoiceMessageResponse response = messageBirdClient.viewVoiceMessage(args[1]);
            System.out.println(response.toString());


        } catch (UnauthorizedException unauthorized) {
            if (unauthorized.getErrors() != null) {
                System.out.println(unauthorized.getErrors().toString());
            }
            unauthorized.printStackTrace();
        } catch (GeneralException generalException) {
            if (generalException.getErrors() != null) {
                System.out.println(generalException.getErrors().toString());
            }
            generalException.printStackTrace();
        } catch (NotFoundException notFoundException) {
            if (notFoundException.getErrors() !=null) {
                System.out.println(notFoundException.getErrors().toString());
            }
            notFoundException.printStackTrace();
        }
    }
}
