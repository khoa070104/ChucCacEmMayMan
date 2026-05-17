package main;

import controller.CandidateController;

/**
 * Entry point – Candidate Management System (J1.L.P0022).
 */
public class Main {

    public static void main(String[] args) {
        CandidateController controller = new CandidateController();
        controller.run();
    }
}
