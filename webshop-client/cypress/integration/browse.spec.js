describe("Browsing products", function () {
    context("Successful log in sequence", function () {
        beforeEach(function () {
            cy.visit("/login");
            cy.clearCookie("access_token")
            cy.server();

            cy.get("[data-cy=username]").type("henk");
            cy.get("[data-cy=password]").type("1234");

            cy.get("[data-cy=loginButton]").click();

            cy.wait(1000);
        });

        it("Browse product", () =>{
            cy.visit("/browse?query=");
            cy.get("[data-cy=minPrice]").type("10");
            cy.get("[data-cy=maxPrice]").type("600").type('{enter}', {force: true});
            
        })
    });
})
