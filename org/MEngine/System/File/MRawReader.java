package org.MEngine.System.File;

import androidx.annotation.RawRes;
import org.MEngine.System.DeviceService.MDevice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MRawReader {
    public static String read(MDevice device, @RawRes int resource) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            device.getActivity().getResources().openRawResource(resource)
                    )
            );

            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}