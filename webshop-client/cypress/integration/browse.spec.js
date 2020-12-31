describe("Browsing products", function () {
    context("Successful log in sequence", function () {
        beforeEach(function () {
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5rIiwianRpIjoiMSIsIlJvbGUiOiJDdXN0b21lciIsImlhdCI6MTYwOTQwODUwNn0.-FiQD0LTRPqbywjC0Qhp9hbH9S_zkPhkYHvtunER0nA")
        });

        it("Browse product", () =>{
            cy.visit("/browse?query=");
            cy.get("[data-cy=minPrice]").type("10");
            cy.get("[data-cy=maxPrice]").type("600").type('{enter}', {force: true});
            cy.wait(1000);
            cy.get("[data-cy=price]").each(function ($el) {
                let price = $el.text().replace("$ ", "");
                expect(price * 100).to.be.greaterThan(1000);
                expect(price * 100).to.be.lessThan(60000);
            })
        })
    });
})
