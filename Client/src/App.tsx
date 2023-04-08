import * as Index from "./components/Index";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import { Route, Routes } from "react-router";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Provider } from "react-redux";
import store from './redux/store';

function App() {
  const queryClient = new QueryClient();

  return (
    <div className="App">
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <BrowserRouter>
            <Index.Navbar />
            <Routes>
              <Route path="/" element={<Index.Intro />} />
              <Route path="/home" element={<Index.Home />} />
              <Route path="/signup" element={<Index.WorkerSignup />} />
              <Route path="/signup/company" element={<Index.CompanySignup />} />
              <Route path="/profile" element={<Index.Profile />} />
              <Route path="/login" element={<Index.Login />} />
              <Route path="/add" element={<Index.AddPosting />} />
              <Route path="/browse" element={<Index.Browse />} />
            </Routes>
            <Index.Footer />
          </BrowserRouter>
        </QueryClientProvider>
      </Provider>
    </div>
  );
}

export default App;
