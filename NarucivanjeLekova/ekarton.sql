--
-- Database: `ekarton`
--

-- --------------------------------------------------------

--
-- Table structure for table `admini`
--

CREATE TABLE `admini` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `adminPrivilegy` tinyint(1) NOT NULL,
  `lekarPrivilegy` tinyint(1) NOT NULL,
  `pacijentPrivilegy` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lekari`
--

CREATE TABLE `lekari` (
  `id` int(11) NOT NULL,
  `ime` varchar(15) NOT NULL,
  `prezime` varchar(35) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `radnoVreme` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lekovi`
--

CREATE TABLE `lekovi` (
  `id` int(11) NOT NULL,
  `ime` varchar(35) NOT NULL,
  `cena` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lekovibolesti`
--

CREATE TABLE `lekovibolesti` (
  `id` int(11) NOT NULL,
  `ime` varchar(35) NOT NULL,
  `cena` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lekoviinfekcije`
--

CREATE TABLE `lekoviinfekcije` (
  `id` int(11) NOT NULL,
  `ime` varchar(35) NOT NULL,
  `cena` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pacijenti`
--

CREATE TABLE `pacijenti` (
  `id` int(11) NOT NULL,
  `ime` varchar(35) NOT NULL,
  `prezime` varchar(35) NOT NULL,
  `adresa` varchar(35) NOT NULL,
  `mobilni` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pacijenti`
--

INSERT INTO `pacijenti` (`id`, `ime`, `prezime`, `adresa`, `mobilni`, `username`, `password`) VALUES
(1, 'm', 'm', 'm', 'm', 'm', 'm'),
(2, 'k', 'k', 'k', 'k', 'k', 'k');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admini`
--
ALTER TABLE `admini`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lekari`
--
ALTER TABLE `lekari`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lekovi`
--
ALTER TABLE `lekovi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lekovibolesti`
--
ALTER TABLE `lekovibolesti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lekoviinfekcije`
--
ALTER TABLE `lekoviinfekcije`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacijenti`
--
ALTER TABLE `pacijenti`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
