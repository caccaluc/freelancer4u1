/// <reference types="cypress" />

describe("Manage jobs as admin", () => {
    before(() => {
        cy.clearAllSessionStorage();
        cy.visit("http://localhost:8080");

        // Als admin einloggen und überprüfen, ob die Willkommenspage angezeigt wird.
        cy.get("#username").type(Cypress.env()["admin"].email);
        cy.get("#password").type(Cypress.env()["admin"].password);
        cy.contains("button", "Log in").click();
        cy.get("h1").should("contain", "Welcome");

        // «admin» bezieht sich auf das entsprechende Objekt der in Schritt d) erstellten Datei cypress.env.json
        cy.request({
            method: "DELETE",
            url: "http://localhost:8080/api/job",
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("jwt_token"),
            },
        });
    });

    // Alle vorhandenen Jobs mit dem in Schritt a) erstellten Endpoint löschen.
    it("visit jobs page", () => {
        cy.get('a[href="/jobs"]').click();
        cy.location("pathname").should("equal", "/jobs");
    });
}); 