describe("Reporting review", function () {
    context("Successful log in sequence", function () {
        beforeEach(function () {
            });

        it("Successfully report a ", () => {
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5rIiwianRpIjoiMSIsIlJvbGUiOiJDdXN0b21lciIsImlhdCI6MTYwOTQwODUwNn0.-FiQD0LTRPqbywjC0Qhp9hbH9S_zkPhkYHvtunER0nA");
            cy.visit("/product/1");
            cy.wait(1000);
            cy.get("[data-cy=report]").click();
            cy.clearCookie("access_token");
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwianRpIjoiMSIsIlJvbGUiOiJSZXRhaWxlciIsImlhdCI6MTYwOTQyMzMwOH0.qYkCmfEweoAbHxO5HiKX2amWWgIQjWTiE_ffKepUeVw");
            cy.visit("/reports");
            cy.contains("body")
        });
    });
})
