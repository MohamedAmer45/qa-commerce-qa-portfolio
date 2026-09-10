describe("Coupons API", () => {
  const applyCoupon = (
    body: Record<string, unknown>,
    failOnStatusCode = true,
  ) => {
    return cy.request({
      method: "POST",
      url: "/api/coupons",
      body,
      failOnStatusCode,
    });
  };

  it("applies SAVE10 and returns a 10% discount", () => {
    applyCoupon({
      code: "SAVE10",
      subtotal: 100,
    }).then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body).to.deep.equal({
        valid: true,
        code: "SAVE10",
        discount: 10,
        freeShipping: false,
      });
    });
  });

  it("rounds SAVE10 discount correctly for decimal subtotal", () => {
    applyCoupon({
      code: "SAVE10",
      subtotal: 33.33,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("SAVE10");

      expect(response.body.discount).to.equal(3.33);
      expect(response.body.freeShipping).to.equal(false);
    });
  });

  it("applies FREESHIP", () => {
    applyCoupon({
      code: "FREESHIP",
      subtotal: 50,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("FREESHIP");
      expect(response.body.discount).to.equal(0);
      expect(response.body.freeShipping).to.equal(true);
    });
  });

  it("accepts MIN100 exactly at the minimum subtotal", () => {
    applyCoupon({
      code: "MIN100",
      subtotal: 100,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("MIN100");
      expect(response.body.discount).to.equal(15);
      expect(response.body.freeShipping).to.equal(false);
    });
  });

  it("accepts MIN100 above the minimum subtotal", () => {
    applyCoupon({
      code: "MIN100",
      subtotal: 100.01,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("MIN100");
      expect(response.body.discount).to.equal(15);
    });
  });

  it("rejects MIN100 below the minimum subtotal", () => {
    applyCoupon(
      {
        code: "MIN100",
        subtotal: 99.99,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);

      expect(response.body).to.deep.equal({
        error: "MINIMUM_NOT_MET",
        minimum: 100,
      });
    });
  });

  it("returns 404 for an unknown coupon", () => {
    applyCoupon(
      {
        code: "UNKNOWN",
        subtotal: 100,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(404);
      expect(response.body.error).to.equal("COUPON_NOT_FOUND");
    });
  });

  it("treats coupon codes as case insensitive", () => {
    applyCoupon({
      code: "save10",
      subtotal: 100,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("SAVE10");
      expect(response.body.discount).to.equal(10);
    });
  });

  it("accepts mixed-case coupon codes", () => {
    applyCoupon({
      code: "SaVe10",
      subtotal: 100,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("SAVE10");
      expect(response.body.discount).to.equal(10);
    });
  });

  it("trims whitespace around coupon codes", () => {
    applyCoupon({
      code: "   SAVE10   ",
      subtotal: 100,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("SAVE10");
      expect(response.body.discount).to.equal(10);
    });
  });

  it("handles a zero subtotal for SAVE10", () => {
    applyCoupon({
      code: "SAVE10",
      subtotal: 0,
    }).then((response) => {
      expect(response.status).to.equal(200);
      expect(response.body.valid).to.equal(true);
      expect(response.body.code).to.equal("SAVE10");
      expect(response.body.discount).to.equal(0);
      expect(response.body.freeShipping).to.equal(false);
    });
  });
});
