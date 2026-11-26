package com.postoffice.controller;

import javafx.fxml.FXML;

public class PostMasterDashboardController extends BaseDashboardController {
    @FXML private TransactionsController transactionsTabController;
    @FXML private MailController mailTabController;
    @FXML private RefundsController refundsTabController;
    @FXML private ComplaintsController complaintsTabController;
    @FXML private FinanceController financeTabController;
    @FXML private AccountsController accountsTabController;
    @FXML private DutiesController dutiesTabController;
    @FXML private AttendanceController attendanceTabController;
    @FXML private ActivityController activityTabController;

    @FXML
    public void initialize() {
        showWelcome();
    }

    @FXML
    private void handleTabChanged() {
        if (transactionsTabController != null) transactionsTabController.refresh();
        if (mailTabController != null) mailTabController.refresh();
        if (refundsTabController != null) refundsTabController.refresh();
        if (complaintsTabController != null) complaintsTabController.refresh();
        if (financeTabController != null) financeTabController.refresh();
        if (accountsTabController != null) accountsTabController.refresh();
        if (dutiesTabController != null) dutiesTabController.refresh();
        if (attendanceTabController != null) attendanceTabController.refresh();
        if (activityTabController != null) activityTabController.refresh();
    }
}
