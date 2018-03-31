
--
-- For databene benerator  (generate database)
--
--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `login` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` enum('CLIENT','ADMIN','BOOKMAKER') CHARACTER SET utf8 NOT NULL,
  `birthday` date NOT NULL,
  `create_date` datetime NOT NULL,
  `doc_numb` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `doc_type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `bets`
--

DROP TABLE IF EXISTS `bets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bets` (
  `bet_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_count` decimal(10,2) NOT NULL,
  `expected_win` decimal(12,2) NOT NULL,
  `result` tinyint(1) DEFAULT '-1',
  `bet_type` varchar(45) CHARACTER SET utf8 NOT NULL,
  `bet_date` datetime NOT NULL,
  `bet_coef` decimal(5,2) NOT NULL,
  `bet_currency` enum('USD','RUB','BYN') CHARACTER SET utf8 NOT NULL,
  `accounts_login` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `events_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`bet_id`),
  KEY `fk_bets_events_idx` (`events_id`),
  KEY `fk_bets_accounts_idx` (`accounts_login`),
  CONSTRAINT `fk_bets_acc` FOREIGN KEY (`accounts_login`) REFERENCES `accounts` (`login`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bets_events` FOREIGN KEY (`events_id`) REFERENCES `events` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inv_currency` enum('USD','EUR','RUB','BYN') CHARACTER SET utf8 NOT NULL,
  `ballance` decimal(12,2) unsigned NOT NULL DEFAULT '0.00',
  `accounts_login` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoices_idx` (`accounts_login`),
  CONSTRAINT `fk_invoices_acc` FOREIGN KEY (`accounts_login`) REFERENCES `accounts` (`login`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coefficients`
--

DROP TABLE IF EXISTS `coefficients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coefficients` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `win_first` decimal(5,2) unsigned DEFAULT NULL,
  `win_second` decimal(5,2) unsigned DEFAULT NULL,
  `nobody` decimal(5,2) unsigned DEFAULT NULL,
  `first_or_nobody` decimal(5,2) unsigned DEFAULT NULL,
  `second_or_nobody` decimal(5,2) unsigned DEFAULT NULL,
  `first_or_second` decimal(5,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `event_type` enum('sport','cybersport') CHARACTER SET utf8 DEFAULT NULL,
  `kind_of_sport` varchar(16) CHARACTER SET utf8 NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_competitor` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `second_competitor` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `first_score` smallint(5) unsigned NOT NULL DEFAULT '0',
  `second_score` smallint(5) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime NOT NULL,
  `is_played` smallint(1) NOT NULL DEFAULT '0',
  `coefficients_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_events_coefficients_idx` (`coefficients_id`),
  CONSTRAINT `fk_events_coefficients` FOREIGN KEY (`coefficients_id`) REFERENCES `coefficients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `type` enum('webmoney','mastercard','visa') CHARACTER SET utf8 DEFAULT NULL,
  `amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `tran_type` enum('Deposit','Withdraw') CHARACTER SET utf8 NOT NULL,
  `invoices_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dep_invoices_idx` (`invoices_id`),
  CONSTRAINT `fk_dep_invoices` FOREIGN KEY (`invoices_id`) REFERENCES `invoices` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

