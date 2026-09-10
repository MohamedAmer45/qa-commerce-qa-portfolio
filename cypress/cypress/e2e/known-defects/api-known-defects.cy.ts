describe("Known API Defects", () => {
  it("BUG-API-ORD-001 - duplicate product lines must use aggregated stock validation", () => {
    cy.request({
      method: "POST",
      url: "/api/orders",
      body: {
        items: [
          {
            id: 2,
            qty: 1,
          },
          {
            id: 2,
            qty: 1,
          },
        ],
        shipping: {
          email: "qa.user@example.com",
          address: "123 QA Street",
        },
        payment: {
          cardNumber: "4242424242424242",
        },
      },
      failOnStatusCode: false,
    }).then((response) => {
      /*
       * Product 2 has stock = 1.
       *
       * Two separate lines request:
       * 1 + 1 = 2 units.
       *
       * Correct behavior:
       * HTTP 409 INSUFFICIENT_STOCK
       *
       * Known defective behavior:
       * The API validates each line separately and accepts the order.
       */

      expect(
        response.status,
        "combined duplicate quantities must not exceed available stock",
      ).to.equal(409);

      expect(response.body.error).to.equal("INSUFFICIENT_STOCK");

      expect(response.body.productId).to.equal(2);

      expect(response.body.available).to.equal(1);
    });
  });
});
