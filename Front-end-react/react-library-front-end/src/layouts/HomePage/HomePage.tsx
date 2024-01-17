import React from "react";
import { ExploreTopBooks } from "./Components/ExploreTopBooks";
import { Carousel } from "./Components/Carousel";
import { LibraryServices } from "./Components/LibraryServices";
import { Heros } from "./Components/Heros";

export const HomePage = () => {
  return (
    <>
      <ExploreTopBooks />
      <Carousel />
      <Heros />
      <LibraryServices />
    </>
  );
};
