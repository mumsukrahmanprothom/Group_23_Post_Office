
public class TestPostOffice {

    public static void main(String[] args) {

        Post_Master manager = new Post_Master();

        Assistant_Post_Master assistant =
                new Assistant_Post_Master(manager);

        // Accept parcel
        Post_Master.Parcel parcel =
                assistant.acceptParcel(
                        "Rahim",
                        "Karim",
                        "Dhaka",
                        2.5
                );

        String id = parcel.getTrackingId();

        // Sort parcel
        assistant.sortParcel(id);

        // Dispatch parcel
        assistant.dispatchParcel(id);

        // Confirm delivery
        assistant.confirmDelivery(id);

        // Track parcel
        assistant.trackParcel(id);

        // Generate report
        manager.generateReport();
    }
}
