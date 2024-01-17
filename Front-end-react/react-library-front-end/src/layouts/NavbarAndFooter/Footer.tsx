export const Footer = () => {
  return (
    <div className="main-color">
      <div className="container d-flex flex-wrap justify-content-between align-items-center py-5 main-color ">
        <p className="col-md-4 mb-0 text-white">
          © Example Library Demo App, Inc
        </p>
        <ul className="nav navbar-dark col-md-4 justify-content-end">
          <li className="nav-item">
            <a href="#" className="nav-linl px-2 text-white">
              Home
            </a>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-linl px-2 text-white">
              Search Books
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
};
